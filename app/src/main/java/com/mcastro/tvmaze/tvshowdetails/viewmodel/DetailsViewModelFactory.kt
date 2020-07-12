package com.mcastro.tvmaze.tvshowdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcastro.tvmaze.infrastructure.tvshow.TvShowsRepository

class DetailsViewModelFactory(
    private val repository: TvShowsRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = DetailsViewModel(
        repository
    ) as T
}