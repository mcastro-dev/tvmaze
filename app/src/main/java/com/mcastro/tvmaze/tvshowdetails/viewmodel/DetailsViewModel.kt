package com.mcastro.tvmaze.tvshowdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mcastro.tvmaze.infrastructure.tvshow.TvShowsRepository

class DetailsViewModel(
    private val repository: TvShowsRepository
) : ViewModel() {

    fun getTvShow(tvShowId: Int) = liveData {
        emitSource(repository.getTvShow(tvShowId))
    }
}