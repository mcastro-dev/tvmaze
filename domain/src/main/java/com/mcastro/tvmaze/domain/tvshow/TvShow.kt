package com.mcastro.tvmaze.domain.tvshow

import com.mcastro.tvmaze.domain.convertToHttps
import com.mcastro.tvmaze.domain.schedule.Schedule

class TvShow(
    val id: Int,
    val name: String,
    posterUrl: String?,
    val schedule: Schedule,
    val genres: List<Genre>,
    val summary: String
) {
    val posterUrl: String? = posterUrl?.convertToHttps()
}