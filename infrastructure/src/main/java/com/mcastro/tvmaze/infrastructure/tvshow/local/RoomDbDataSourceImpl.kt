package com.mcastro.tvmaze.infrastructure.tvshow.local

import android.content.Context
import com.mcastro.tvmaze.domain.tvshow.TvShowPreview
import com.mcastro.tvmaze.infrastructure.tvshow.local.database.TvShowsDatabase
import com.mcastro.tvmaze.infrastructure.tvshow.local.database.dao.PreviewsDao
import com.mcastro.tvmaze.infrastructure.tvshow.local.mappers.LocalTvShowPreviewMapper

class RoomDbDataSourceImpl private constructor(
    private var previewsDao: PreviewsDao
) : TvShowsLocalDataSource {

    companion object {
        private var instance: TvShowsLocalDataSource? = null

        fun getInstance(context: Context) : TvShowsLocalDataSource {
            if (instance == null) {
                val database: TvShowsDatabase = TvShowsDatabase.getInstance(context)
                instance = RoomDbDataSourceImpl(database.previewsDao())
            }
            return instance!!
        }
    }

    override fun getPreviewsPaginating(page: Int, take: Int) = previewsDao.getAllPaginating(take, page * take)

    override suspend fun insertPreviews(previews: List<TvShowPreview>) {
        previewsDao.insert(LocalTvShowPreviewMapper.toLocalDataTransferObjects(previews))
    }
}