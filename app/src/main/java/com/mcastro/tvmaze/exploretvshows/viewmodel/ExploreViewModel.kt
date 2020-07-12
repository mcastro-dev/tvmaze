package com.mcastro.tvmaze.exploretvshows.viewmodel

import androidx.lifecycle.*
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.DataOrFailure
import com.mcastro.tvmaze.infrastructure.tvshow.TvShowsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val ITEMS_COUNT_PER_PAGE = 50

class TvShowsPreviewsViewModel(
    private val repository: TvShowsRepository
) : ViewModel() {
    private var page = 0

    private val _initialLoading = MutableLiveData<Boolean>()
    val initialLoading = _initialLoading

    private var _tvShowsPreviews = MutableLiveData<DataOrFailure<List<TvShowPreview>>>()
    val tvShowsPreviews = _tvShowsPreviews

    init {
        initialFetch()
    }

    fun nextPage() = viewModelScope.launch {
        page++

        repository.getPreviewsPaginating(page, ITEMS_COUNT_PER_PAGE).collect {
            _tvShowsPreviews.value = it
        }
    }

    private fun initialFetch() = viewModelScope.launch {
        _initialLoading.value = true

        repository.getPreviewsPaginating(page, ITEMS_COUNT_PER_PAGE).collect {
            _tvShowsPreviews.value = it
        }

        _initialLoading.value = false
    }
}