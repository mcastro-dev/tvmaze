package com.mcastro.tvmaze.application.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mcastro.tvmaze.domain.tvshow.TvShow
import com.mcastro.tvmaze.infrastructure.DataOrFailure
import com.mcastro.tvmaze.infrastructure.tvshow.TvShowsRepository

class TvShowViewModel(
    private val repository: TvShowsRepository
) : ViewModel() {

    fun getTvShow(tvShowId: Int) = liveData {
        emitSource(repository.getTvShow(tvShowId))
    }
}