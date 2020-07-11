package com.mcastro.tvmaze.application.showspreviews

import androidx.lifecycle.*
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.DataOrFailure
import com.mcastro.tvmaze.infrastructure.RemoteTvShowPreviewsFetchException
import com.mcastro.tvmaze.infrastructure.tvshow.TvShowsRepository

class TvShowsPreviewsViewModel(
    private val repository: TvShowsRepository
) : ViewModel() {
    // TODO: Loading
    // TODO: Pagination
    private var page = 0

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading

    val tvShowsPreviews: LiveData<DataOrFailure<List<TvShowPreview>>>
            = liveData {
        _loading.value = true
        emitSource(repository.getPreviewsPaginating(page))
        _loading.value = false
    }
}