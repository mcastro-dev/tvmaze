package com.mcastro.tvmaze.domain.season

data class Season(
    val id: Int,
    val number: Int,
    val episodes: List<Episode>
)