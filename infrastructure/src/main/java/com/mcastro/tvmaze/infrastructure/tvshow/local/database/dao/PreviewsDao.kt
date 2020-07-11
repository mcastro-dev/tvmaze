package com.mcastro.tvmaze.infrastructure.tvshow.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mcastro.tvmaze.infrastructure.tvshow.local.local_data_transfer_objects.TvShowPreviewLDTO

@Dao
interface PreviewsDao {
    @Query("SELECT * FROM tv_show_preview LIMIT :limit OFFSET :offset")
    fun getAllPaginating(limit: Int, offset: Int): LiveData<List<TvShowPreviewLDTO>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(previews: List<TvShowPreviewLDTO>)

    //TODO
    /*@Query("SELECT * FROM tv_show_preview WHERE name LIKE :first")
    fun findByName(first: String): List<TvShowPreviewLDTO>*/
}