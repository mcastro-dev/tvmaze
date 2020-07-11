package com.mcastro.tvmaze.infrastructure.tvshow.remote.mappers

import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.tvshow.remote.remote_data_transfer_objetcs.TvShowPreviewRDTO

class RemoteTvShowPreviewMapper {
    fun toDomainModel(
        tvShowPreviewRDTO: TvShowPreviewRDTO
    ) = TvShowPreview(
        tvShowPreviewRDTO.id,
        tvShowPreviewRDTO.name,
        tvShowPreviewRDTO.posterUrl
    )

    fun toDomainModels(
        tvShowPreviewRDTOs: List<TvShowPreviewRDTO>
    ) = tvShowPreviewRDTOs.map {
        toDomainModel(
            it
        )
    }
}