package com.mcastro.tvmaze

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mcastro.tvmaze.application.showspreviews.TvShowsPreviewsViewModel
import com.mcastro.tvmaze.application.showspreviews.TvShowsPreviewsViewModelFactory
import com.mcastro.tvmaze.databinding.ActivityMainBinding
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.tvshow.TvShowsRepositoryImpl
import com.mcastro.tvmaze.infrastructure.tvshow.local.RoomDbDataSourceImpl
import com.mcastro.tvmaze.infrastructure.tvshow.remote.TvMazeDataSourceImpl

class MainActivity : AppCompatActivity(), TvShowClickListener {

    private val viewModel: TvShowsPreviewsViewModel by viewModels {
        // TODO: Dependency Injection
        TvShowsPreviewsViewModelFactory(
            TvShowsRepositoryImpl(
                TvMazeDataSourceImpl.getInstance(),
                RoomDbDataSourceImpl.getInstance(applicationContext)
            )
        )
    }

    private val recyclerAdapter = TvShowsPreviewRecyclerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupPreviewsRecyclerView(binding.listPreviews)
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
        val intent = TvShowDetailsActivity.intentFor(this, tvShowPreview)
        startActivity(intent)
    }

    private fun setupPreviewsRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = recyclerAdapter
    }

    private fun observeViewModel() {
        viewModel.loading.observe(this, Observer {  isLoading ->
            if (isLoading) {
                // TODO: show loading indicator
            } else {
                // TODO: remove loading indicator
            }
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
