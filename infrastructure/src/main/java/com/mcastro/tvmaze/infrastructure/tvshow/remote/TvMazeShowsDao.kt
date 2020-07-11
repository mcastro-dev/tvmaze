package com.mcastro.tvmaze.infrastructure.tvshow.remote

import com.mcastro.tvmaze.infrastructure.tvshow.remote.remote_data_transfer_objetcs.TvShowPreviewRDTO
import com.mcastro.tvmaze.infrastructure.tvshow.remote.remote_data_transfer_objetcs.TvShowRDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeShowsDao {

    @GET("/shows?")
    suspend fun getPreviewsPaginating(@Query("page") page: Int): List<TvShowPreviewRDTO>

    @GET("/shows/{id}")
    suspend fun getTvShow(@Path("id") id: Int): TvShowRDTO
}