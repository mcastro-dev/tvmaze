package com.mcastro.tvmaze.searchtvshow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcastro.tvmaze.infrastructure.tvshow.TvShowsRepository

class SearchTvShowViewModelFactory(
    private val repository: TvShowsRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = SearchTvShowViewModel(
        repository
    ) as T
}