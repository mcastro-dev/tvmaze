package com.mcastro.tvmaze.infrastructure.tvshow

import androidx.lifecycle.LiveData
import com.mcastro.tvmaze.domain.tvshow.TvShow
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.DataOrFailure
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {
    suspend fun getPreviewsPaginating(page: Int, take: Int) : Flow<DataOrFailure<List<TvShowPreview>>>
    suspend fun getTvShow(tvShowId: Int) : LiveData<DataOrFailure<TvShow>>
}