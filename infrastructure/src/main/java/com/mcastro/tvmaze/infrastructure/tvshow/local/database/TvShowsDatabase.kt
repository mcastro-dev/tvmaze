package com.mcastro.tvmaze.infrastructure.tvshow.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mcastro.tvmaze.infrastructure.tvshow.local.database.dao.PreviewsDao
import com.mcastro.tvmaze.infrastructure.tvshow.local.local_data_transfer_objects.TvShowPreviewLDTO

@Database(entities = [TvShowPreviewLDTO::class], version = 1, exportSchema = false)
abstract class TvShowsDatabase : RoomDatabase() {
    abstract fun previewsDao(): PreviewsDao

    companion object {
        private var instance: TvShowsDatabase? = null

        fun getInstance(context: Context) : TvShowsDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    TvShowsDatabase::class.java,
                    "TvShows.db"
                ).build()
            }
            return instance!!
        }
    }
}