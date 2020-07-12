package com.mcastro.tvmaze.searchtvshow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcastro.tvmaze.infrastructure.tvshow.TvShowsRepository

class SearchViewModelFactory(
    private val repository: TvShowsRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = SearchViewModel(
        repository
    ) as T
}