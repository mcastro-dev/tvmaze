package com.mcastro.tvmaze.searchtvshow.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.DataOrFailure
import com.mcastro.tvmaze.infrastructure.tvshow.TvShowsRepository
import kotlinx.coroutines.launch

class SearchTvShowViewModel(
    private val repository: TvShowsRepository
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading

    private var _searchedTvShows = MutableLiveData<DataOrFailure<List<TvShowPreview>>>()
    val searchedTvShows = _searchedTvShows

    fun searchTvShowByName(query: String) = viewModelScope.launch {
        _loading.value = true
        _searchedTvShows.value = repository.searchTvShowByName(query)
        _loading.value = false
    }
}