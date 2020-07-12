package com.mcastro.tvmaze.common

import android.content.Context
import com.mcastro.tvmaze.R
import com.mcastro.tvmaze.infrastructure.TvShowUnexpectedException
import com.mcastro.tvmaze.infrastructure.UnavailableCommunicationWithHost

object ErrorMessageFactory {
    fun create(context: Context, exception: Exception) : String = when(exception) {
        is TvShowUnexpectedException -> context.getString(R.string.generic_error)
        is UnavailableCommunicationWithHost -> context.getString(R.string.unavailable_communication_error)
        else -> throw Exception("Not implemented")
    }
}