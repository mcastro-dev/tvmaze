package com.mcastro.tvmaze.infrastructure.tvshow.local.database.dao

import androidx.room.*
import com.mcastro.tvmaze.infrastructure.tvshow.local.local_data_transfer_objects.TvShowPreviewLDTO

@Dao
interface PreviewsDao {
    @Query("SELECT * FROM tv_show_preview ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getAllPaginating(limit: Int, offset: Int): List<TvShowPreviewLDTO>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(previews: List<TvShowPreviewLDTO>)

    @Query("SELECT * FROM tv_show_preview WHERE name LIKE :name")
    suspend fun findByName(name: String): List<TvShowPreviewLDTO>
}