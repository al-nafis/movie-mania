package com.mnafis.movie_mania.room_database

import android.util.Log
import com.mnafis.movie_mania.MOVIE_MANIA_LOG_KEY
import com.mnafis.movie_mania.models.MovieDetails
import javax.inject.Inject

class MovieManiaRepository @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend fun getAllMovies(): List<MovieDetails> = try {
        movieDao.getAllMovies()
    } catch (e: Exception) {
        Log.d(MOVIE_MANIA_LOG_KEY, e.message.toString())
        emptyList()
    }

    suspend fun getMovie(id: String): MovieDetails? = try {
        movieDao.getMovie(id)
    } catch (e: Exception) {
        Log.d(MOVIE_MANIA_LOG_KEY, e.message.toString())
        null
    }

    suspend fun removeMovie(movieDetails: MovieDetails) = try {
        movieDao.removeMovie(movieDetails)
    } catch (e: Exception) {
        Log.d(MOVIE_MANIA_LOG_KEY, e.message.toString())
    }

    suspend fun addMovie(movieDetails: MovieDetails) = try {
        movieDao.addMovie(movieDetails)
    } catch (e: Exception) {
        Log.d(MOVIE_MANIA_LOG_KEY, e.message.toString())
    }
}