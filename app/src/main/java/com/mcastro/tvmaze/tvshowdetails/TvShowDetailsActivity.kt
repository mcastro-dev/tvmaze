package com.mcastro.tvmaze.tvshowdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import com.mcastro.tvmaze.tvshowdetails.viewmodel.TvShowViewModel
import com.mcastro.tvmaze.tvshowdetails.viewmodel.TvShowViewModelFactory
import com.mcastro.tvmaze.common.ErrorMessageDisplayer
import com.mcastro.tvmaze.databinding.ActivityTvShowDetailsBinding
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.tvshow.TvShowsRepositoryImpl
import com.mcastro.tvmaze.infrastructure.tvshow.local.RoomDbDataSourceImpl
import com.mcastro.tvmaze.infrastructure.tvshow.remote.TvMazeDataSourceImpl
import com.squareup.picasso.Picasso

class TvShowDetailsActivity : AppCompatActivity() {

    companion object {
        private const val KEY_TV_SHOW_PREVIEW = "tv_show_preview"
        fun intentFor(context: Context, tvShowPreview: TvShowPreview): Intent {
            return Intent(context, TvShowDetailsActivity::class.java).apply {
                putExtra(KEY_TV_SHOW_PREVIEW, tvShowPreview)
            }
        }
    }

    private val viewModel: TvShowViewModel by viewModels {
        // TODO: Use a Dependency Injection lib
        TvShowViewModelFactory(
            TvShowsRepositoryImpl(
                TvMazeDataSourceImpl.getInstance(),
                RoomDbDataSourceImpl.getInstance(applicationContext)
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTvShowDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Populate view with the data we already have
        val tvShowPreview = intent.getSerializableExtra(KEY_TV_SHOW_PREVIEW) as TvShowPreview
        supportActionBar?.title = tvShowPreview.name
        Picasso.get().load(tvShowPreview.posterUrl).into(binding.imgPoster)

        // Get the data we still need
        viewModel.getTvShow(tvShowPreview.id).observe(this, Observer {
            if (!it.hasData) {
                ErrorMessageDisplayer.show(this, it.failure!!)
                return@Observer
            }
            val data = it.data!!
            binding.run {
                txtGenres.text = data.genres.joinToString(separator = ", ", postfix = ".")
                txtSchedule.text = data.schedule.toString()
                txtSummary.text = HtmlCompat.fromHtml(data.summary, HtmlCompat.FROM_HTML_MODE_COMPACT)
            }
        })
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
}