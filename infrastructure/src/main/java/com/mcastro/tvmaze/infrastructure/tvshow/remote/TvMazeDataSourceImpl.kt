package com.mcastro.tvmaze.infrastructure.tvshow.remote

import com.mcastro.tvmaze.domain.tvshow.TvShow
import com.mcastro.tvmaze.infrastructure.RetrofitHttpClient
import com.mcastro.tvmaze.infrastructure.tvshow.remote.mappers.RemoteTvShowMapper
import com.mcastro.tvmaze.infrastructure.tvshow.remote.mappers.RemoteTvShowPreviewMapper

class TvMazeDataSourceImpl private constructor(
    private var tvMazeShowsDao: TvMazeShowsDao
) : TvShowsRemoteDataSource {

    companion object {
        private const val BASE_URL = "https://api.tvmaze.com"

        private var instance: TvShowsRemoteDataSource? = null

        fun getInstance() : TvShowsRemoteDataSource {
            if (instance == null) {
                val tvMazeShowsDao = RetrofitHttpClient(BASE_URL)
                    .client
                    .create(TvMazeShowsDao::class.java)
                instance = TvMazeDataSourceImpl(tvMazeShowsDao)
            }
            return instance!!
        }
    }

    override suspend fun getPreviewsPaginating(page: Int)
            = RemoteTvShowPreviewMapper().toDomainModels(tvMazeShowsDao.getPreviewsPaginating(page))

    override suspend fun getTvShow(tvShowId: Int)
            = RemoteTvShowMapper().toDomainModel(tvMazeShowsDao.getTvShow(tvShowId))
}