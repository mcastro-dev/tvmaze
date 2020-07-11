package com.mcastro.tvmaze.infrastructure.tvshow.local.mappers

import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.tvshow.local.local_data_transfer_objects.TvShowPreviewLDTO

object LocalTvShowPreviewMapper {
    fun toDomainModel(
        tvShowPreviewLDTO: TvShowPreviewLDTO
    ) = TvShowPreview(
        tvShowPreviewLDTO.id,
        tvShowPreviewLDTO.name,
        tvShowPreviewLDTO.posterUrl
    )

    fun toDomainModels(
        tvShowPreviewLDTOs: List<TvShowPreviewLDTO>
    ) = tvShowPreviewLDTOs.map {
        toDomainModel(
            it
        )
    }

    fun toLocalDataTransferObject(
        tvShowPreview: TvShowPreview
    ) = TvShowPreviewLDTO(
        tvShowPreview.id,
        tvShowPreview.name,
        tvShowPreview.posterUrl
    )

    fun toLocalDataTransferObjects(
        tvShowPreviews: List<TvShowPreview>
    ) = tvShowPreviews.map {
        toLocalDataTransferObject(
            it
        )
    }
}