package com.mcastro.tvmaze.infrastructure.tvshow.remote.remote_data_transfer_objetcs

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class TvShowPreviewRDTO (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    @JsonAdapter(PosterUrlDeserializer::class)
    val posterUrl: String?
)