package com.mcastro.tvmaze.infrastructure.tvshow

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mcastro.tvmaze.domain.tvshow.TvShow
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.DataOrFailure

interface TvShowsRepository {
    suspend fun getPreviewsPaginating(page: Int) : LiveData<DataOrFailure<List<TvShowPreview>>>
    suspend fun getTvShow(tvShowId: Int) : LiveData<DataOrFailure<TvShow>>
}