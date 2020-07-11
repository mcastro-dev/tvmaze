package com.mcastro.tvmaze.infrastructure.tvshow.remote.remote_data_transfer_objetcs

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class TvShowRDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    @JsonAdapter(PosterUrlDeserializer::class)
    val posterUrl: String?,
    @SerializedName("schedule")
    val schedule: ScheduleRDTO,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("summary")
    val summary: String
)

data class ScheduleRDTO(
    @SerializedName("time")
    val time: String,
    @SerializedName("days")
    val days: List<String>
)