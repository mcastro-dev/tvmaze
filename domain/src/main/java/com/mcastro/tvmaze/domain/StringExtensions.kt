package com.mcastro.tvmaze.domain

fun String.convertToHttps() = this.replace("http://", "https://")