package com.mcastro.tvmaze.common

import com.mcastro.tvmaze.domain.tvshow.TvShowPreview

interface TvShowPreviewClickListener {
    fun onTvShowClicked(tvShowPreview: TvShowPreview)
}