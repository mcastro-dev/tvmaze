package com.mcastro.tvmaze.infrastructure

data class DataOrFailure<T>(
    val data: T? = null,
    val failure: Exception? = null
) {
    val hasData = data != null
}