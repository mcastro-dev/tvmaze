package com.mcastro.tvmaze.infrastructure.tvshow.remote.mappers

import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.tvshow.remote.remote_data_transfer_objetcs.TvShowSearchResultRDTO

class RemoteTvShowSearchResultMapper {
    fun toDomainModel(
        tvShowSearchResultRDTO: TvShowSearchResultRDTO
    ) = TvShowPreview(
        tvShowSearchResultRDTO.show.id,
        tvShowSearchResultRDTO.show.name,
        tvShowSearchResultRDTO.show.posterUrl
    )

    fun toDomainModels(
        tvShowSearchResultRDTOs: List<TvShowSearchResultRDTO>
    ) = tvShowSearchResultRDTOs.map {
        toDomainModel(
            it
        )
    }
}