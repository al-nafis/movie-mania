package com.mnafis.compose_ui_android_experiment.movie_mania.local_database

import androidx.room.*
import com.mnafis.compose_ui_android_experiment.movie_mania.models.MovieDetails

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_details")
    suspend fun getAllMovies(): List<MovieDetails>

    @Query("SELECT * FROM movie_details WHERE imdbID LIKE :id LIMIT 1")
    suspend fun getMovie(id: String): MovieDetails?

    @Delete
    suspend fun removeMovie(movieDetails: MovieDetails)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movieDetails: MovieDetails)
}