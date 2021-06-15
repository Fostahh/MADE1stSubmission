package com.mohammadazri.made1stsubmission.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohammadazri.made1stsubmission.core.data.source.local.entity.MovieEntity
import com.mohammadazri.made1stsubmission.core.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class MovieTvShowDatabase : RoomDatabase() {

    abstract fun movieTvShowDao(): MovieTvShowDao
}