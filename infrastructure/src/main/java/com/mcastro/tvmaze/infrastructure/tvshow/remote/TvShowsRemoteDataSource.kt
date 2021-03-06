package com.mcastro.tvmaze.infrastructure.tvshow.remote

import com.mcastro.tvmaze.domain.tvshow.TvShow
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview

interface TvShowsRemoteDataSource {
    suspend fun getPreviewsPaginating(page: Int, take: Int) : List<TvShowPreview>
    suspend fun getTvShow(tvShowId: Int) : TvShow
    suspend fun searchTvShowByName(query: String) : List<TvShowPreview>
}