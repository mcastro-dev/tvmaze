package com.mcastro.tvmaze.infrastructure.tvshow

import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.DataOrFailure
import com.mcastro.tvmaze.infrastructure.RemoteTvShowFetchException
import com.mcastro.tvmaze.infrastructure.RemoteTvShowPreviewsFetchException
import com.mcastro.tvmaze.infrastructure.tvshow.local.mappers.LocalTvShowPreviewMapper
import com.mcastro.tvmaze.infrastructure.tvshow.local.TvShowsLocalDataSource
import com.mcastro.tvmaze.infrastructure.tvshow.remote.TvShowsRemoteDataSource
import kotlinx.coroutines.Dispatchers
import java.net.UnknownHostException

// The only available remoteDataSource right now
//  doesn't have a parameter for limiting results and their value is 250.
private const val ITEMS_COUNT_PER_PAGE = 250

class TvShowsRepositoryImpl(
    private val remoteDataSource: TvShowsRemoteDataSource,
    private val localDataSource: TvShowsLocalDataSource
) : TvShowsRepository {

    override suspend fun getPreviewsPaginating(
        page: Int
    ) = liveData(Dispatchers.IO) {
        val localPreviews = localDataSource
            .getPreviewsPaginating(page, ITEMS_COUNT_PER_PAGE)
            .map { DataOrFailure(LocalTvShowPreviewMapper.toDomainModels(it)) }

        emitSource(localPreviews)

        try {
            val remotePreviews = remoteDataSource.getPreviewsPaginating(page)
            localDataSource.insertPreviews(remotePreviews)

        } catch (e: UnknownHostException) {
            emit(DataOrFailure<List<TvShowPreview>>(
                failure = RemoteTvShowPreviewsFetchException()
            ))
        } // TODO: properly handle other exceptions
    }

    override suspend fun getTvShow(
        tvShowId: Int
    ) = liveData(Dispatchers.IO) {
        // TODO: cache data in local database and retrieve from there

        try {
            emit(DataOrFailure(remoteDataSource.getTvShow(tvShowId)))

        } catch (e: UnknownHostException) {
            emit(DataOrFailure(failure = RemoteTvShowFetchException()))
        } // TODO: properly handle other exceptions
    }
}