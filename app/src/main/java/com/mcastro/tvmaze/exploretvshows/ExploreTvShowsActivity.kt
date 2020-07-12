package com.mcastro.tvmaze.exploretvshows

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mcastro.tvmaze.*
import com.mcastro.tvmaze.application.showspreviews.TvShowsPreviewsViewModel
import com.mcastro.tvmaze.application.showspreviews.TvShowsPreviewsViewModelFactory
import com.mcastro.tvmaze.databinding.ActivityMainBinding
import com.mcastro.tvmaze.tvshowdetails.TvShowDetailsActivity
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.tvshow.TvShowsRepositoryImpl
import com.mcastro.tvmaze.infrastructure.tvshow.local.RoomDbDataSourceImpl
import com.mcastro.tvmaze.infrastructure.tvshow.remote.TvMazeDataSourceImpl

class ExploreTvShowsActivity : AppCompatActivity(),
    TvShowClickListener {

    private val viewModel: TvShowsPreviewsViewModel by viewModels {
        // TODO: Dependency Injection
        TvShowsPreviewsViewModelFactory(
            TvShowsRepositoryImpl(
                TvMazeDataSourceImpl.getInstance(),
                RoomDbDataSourceImpl.getInstance(applicationContext)
            )
        )
    }

    private var binding: ActivityMainBinding? = null
    private val recyclerAdapter =
        TvShowsPreviewRecyclerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding!!.root)
        setSupportActionBar(binding!!.toolbar)

        setupPreviewsRecyclerView(binding!!.listPreviews)
        observeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onTvShowClicked(tvShowPreview: TvShowPreview) {
        val intent =
            TvShowDetailsActivity.intentFor(
                this,
                tvShowPreview
            )
        startActivity(intent)
    }

    private fun setupNestedScrollViewForPagination(nestedScrollView: NestedScrollView) {
        val listener = NestedScrollView.OnScrollChangeListener { view, _, scrollY, _, _ ->
            if (scrollY == view.getChildAt(0).measuredHeight - view.measuredHeight) {
                viewModel.nextPage()
            }
        }
        nestedScrollView.setOnScrollChangeListener(listener)
    }

    private fun setupPreviewsRecyclerView(recyclerView: RecyclerView) {
        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = recyclerAdapter

        val listener = object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
            override fun onLoadMore() {
                viewModel.nextPage()
            }
        }
        recyclerView.addOnScrollListener(listener)
    }

    private fun observeViewModel() {
        viewModel.initialLoading.observe(this, Observer { isLoading ->
            binding?.listPreviews?.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
            binding?.initialLoadingIndicator?.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.tvShowsPreviews.observe(this, Observer {
            if (!it.hasData) {
                // TODO: show friendly error message to user, according to exception
                return@Observer
            }
            recyclerAdapter.addData(it.data!!)
        })
    }
}
