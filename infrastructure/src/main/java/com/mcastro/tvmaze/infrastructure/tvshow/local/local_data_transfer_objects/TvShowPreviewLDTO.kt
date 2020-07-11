package com.mcastro.tvmaze.infrastructure.tvshow.local.local_data_transfer_objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show_preview")
data class TvShowPreviewLDTO(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "poster_url")
    val posterUrl: String?
)