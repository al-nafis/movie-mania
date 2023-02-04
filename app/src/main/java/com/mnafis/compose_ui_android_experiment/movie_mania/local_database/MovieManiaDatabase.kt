package com.mnafis.compose_ui_android_experiment.movie_mania.local_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mnafis.compose_ui_android_experiment.movie_mania.models.MovieDetails

@Database(entities = [MovieDetails::class], version = 1)
abstract class MovieManiaDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}