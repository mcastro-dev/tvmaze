package com.mcastro.tvmaze.infrastructure.tvshow.local

import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.tvshow.local.local_data_transfer_objects.TvShowPreviewLDTO

interface TvShowsLocalDataSource {
    suspend fun getPreviewsPaginating(page: Int, take: Int): List<TvShowPreviewLDTO>
    suspend fun insertPreviews(previews: List<TvShowPreview>)
}