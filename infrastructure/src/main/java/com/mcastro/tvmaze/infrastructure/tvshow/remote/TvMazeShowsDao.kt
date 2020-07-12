package com.mcastro.tvmaze.infrastructure.tvshow.remote

import com.mcastro.tvmaze.infrastructure.tvshow.remote.remote_data_transfer_objetcs.TvShowPreviewRDTO
import com.mcastro.tvmaze.infrastructure.tvshow.remote.remote_data_transfer_objetcs.TvShowRDTO
import com.mcastro.tvmaze.infrastructure.tvshow.remote.remote_data_transfer_objetcs.TvShowSearchResultRDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeShowsDao {

    @GET("/shows?")
    suspend fun getPreviewsPaginating(@Query("page") page: Int): List<TvShowPreviewRDTO>

    @GET("/shows/{id}")
    suspend fun getTvShow(@Path("id") tvShowId: Int): TvShowRDTO

    // /search/shows?q=girls
    @GET("/search/shows?")
    suspend fun searchTvShowByName(@Query("q") query: String): List<TvShowSearchResultRDTO>
}