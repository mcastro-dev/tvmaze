package com.mcastro.tvmaze.searchtvshow

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mcastro.tvmaze.common.ErrorMessageDisplayer
import com.mcastro.tvmaze.R
import com.mcastro.tvmaze.application.searchshow.SearchTvShowViewModel
import com.mcastro.tvmaze.application.searchshow.SearchTvShowViewModelFactory
import com.mcastro.tvmaze.common.TvShowPreviewClickListener
import com.mcastro.tvmaze.databinding.ActivitySearchTvShowBinding
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.tvshow.TvShowsRepositoryImpl
import com.mcastro.tvmaze.infrastructure.tvshow.local.RoomDbDataSourceImpl
import com.mcastro.tvmaze.infrastructure.tvshow.remote.TvMazeDataSourceImpl
import com.mcastro.tvmaze.tvshowdetails.TvShowDetailsActivity

class SearchTvShowActivity : AppCompatActivity(), TvShowPreviewClickListener {
    companion object {
        fun intentFor(context: Context): Intent {
            return Intent(context, SearchTvShowActivity::class.java)
        }
    }

    private val viewModel: SearchTvShowViewModel by viewModels {
        // TODO: Use a Dependency Injection lib
        SearchTvShowViewModelFactory(
            TvShowsRepositoryImpl(
                TvMazeDataSourceImpl.getInstance(),
                RoomDbDataSourceImpl.getInstance(applicationContext)
            )
        )
    }

    private var binding: ActivitySearchTvShowBinding? = null
    private val recyclerAdapter = SearchTvShowRecyclerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchTvShowBinding.inflate(layoutInflater)

        setContentView(binding!!.root)
        setSupportActionBar(binding!!.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupRecyclerView(binding!!.listSearch)
        observeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_tv_show_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchActionItem = menu?.findItem(R.id.action_search)
        val searchView = searchActionItem?.actionView as? SearchView

        searchView?.apply {
            setIconifiedByDefault(false)
            queryHint = getString(R.string.hint_search_tv_show)
            requestFocus()
        }

        val onQueryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchTvShowByName(it)
                }

                searchView?.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        }

        searchView?.run {
            setSearchableInfo(manager.getSearchableInfo(componentName))
            setOnQueryTextListener(onQueryTextListener)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onTvShowClicked(tvShowPreview: TvShowPreview) {
        val intent = TvShowDetailsActivity.intentFor(this, tvShowPreview)
        startActivity(intent)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter
    }

    private fun observeViewModel() {
        viewModel.loading.observe(this, Observer { isLoading ->
            binding?.loadingIndicator?.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.searchedTvShows.observe(this, Observer {
            if (!it.hasData) {
                ErrorMessageDisplayer.show(this, it.failure!!)
                return@Observer
            }

            recyclerAdapter.setData(it.data!!)
            // TODO: handle empty result in a user friendly way
        })
    }
}
