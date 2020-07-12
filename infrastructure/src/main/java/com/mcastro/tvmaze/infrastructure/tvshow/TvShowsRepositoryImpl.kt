package com.mcastro.tvmaze.infrastructure.tvshow

import androidx.lifecycle.liveData
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.DataOrFailure
import com.mcastro.tvmaze.infrastructure.RemoteTvShowFetchException
import com.mcastro.tvmaze.infrastructure.RemoteTvShowPreviewsFetchException
import com.mcastro.tvmaze.infrastructure.tvshow.local.mappers.LocalTvShowPreviewMapper
import com.mcastro.tvmaze.infrastructure.tvshow.local.TvShowsLocalDataSource
import com.mcastro.tvmaze.infrastructure.tvshow.remote.TvShowsRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class TvShowsRepositoryImpl(
    private val remoteDataSource: TvShowsRemoteDataSource,
    private val localDataSource: TvShowsLocalDataSource
) : TvShowsRepository {

    override suspend fun getPreviewsPaginating(
        page: Int,
        take: Int
    ) = callbackFlow {

        suspend fun getLocalPreviews(): List<TvShowPreview> {
            return localDataSource
                .getPreviewsPaginating(page, take)
                .map { LocalTvShowPreviewMapper.toDomainModel(it) }
        }

        try {
            val localPreviews = getLocalPreviews()

            // We have cached data
            if (localPreviews.isNotEmpty()) {
                offer(DataOrFailure(localPreviews))

            } else {
                // We do NOT have cached data

                val remotePreviews = remoteDataSource.getPreviewsPaginating(page, take)
                localDataSource.insertPreviews(remotePreviews)

                val updatedLocalPreviews = getLocalPreviews()
                offer(DataOrFailure(updatedLocalPreviews))
            }

        } catch (e: UnknownHostException) {
            offer(DataOrFailure<List<TvShowPreview>>(
                failure = RemoteTvShowPreviewsFetchException()
            ))

            // TODO: properly handle other exceptions

        } finally {
            close()
        }

    }.flowOn(Dispatchers.IO)

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