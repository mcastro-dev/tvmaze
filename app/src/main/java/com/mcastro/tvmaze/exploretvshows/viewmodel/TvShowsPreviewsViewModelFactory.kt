package com.mcastro.tvmaze.exploretvshows.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcastro.tvmaze.infrastructure.tvshow.TvShowsRepository

class TvShowsPreviewsViewModelFactory(private val repository: TvShowsRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = TvShowsPreviewsViewModel(
        repository
    ) as T
}