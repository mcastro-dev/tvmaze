package com.mcastro.tvmaze.infrastructure.tvshow.local

import androidx.lifecycle.LiveData
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.tvshow.local.local_data_transfer_objects.TvShowPreviewLDTO

interface TvShowsLocalDataSource {
    fun getPreviewsPaginating(page: Int, take: Int): LiveData<List<TvShowPreviewLDTO>>
    suspend fun insertPreviews(previews: List<TvShowPreview>)
}