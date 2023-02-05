package com.mnafis.movie_mania.room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mnafis.movie_mania.models.MovieDetails

@Database(entities = [MovieDetails::class], version = 1)
abstract class MovieManiaDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}