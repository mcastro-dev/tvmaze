package com.mcastro.tvmaze.infrastructure.tvshow.remote.remote_data_transfer_objetcs

import com.google.gson.annotations.SerializedName

data class TvShowSearchResultRDTO(
    @SerializedName("show")
    val show: TvShowPreviewRDTO
)