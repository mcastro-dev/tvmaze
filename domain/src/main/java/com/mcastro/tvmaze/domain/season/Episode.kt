package com.mcastro.tvmaze.domain.season

data class Episode(
    val id: Int,
    val name: String,
    val seasonNumber: Int,
    val number: Int,
    val summary: String,
    val imageUrl: String
)