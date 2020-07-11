package com.mcastro.tvmaze.domain.tvshow

import com.mcastro.tvmaze.domain.convertToHttps
import java.io.Serializable

class TvShowPreview(
    val id: Int,
    val name: String,
    posterUrl: String?
) : Serializable {
    val posterUrl: String? = posterUrl?.convertToHttps()
}