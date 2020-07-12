package com.mcastro.tvmaze.common

import android.content.Context
import android.widget.Toast

object ErrorMessageDisplayer {
    fun show(
        context: Context,
        exception: Exception
    ) {
        val message = ErrorMessageFactory.create(context, exception)
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}