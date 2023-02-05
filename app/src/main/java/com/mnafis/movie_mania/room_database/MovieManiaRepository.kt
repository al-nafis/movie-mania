package com.mnafis.movie_mania.room_database

import com.mnafis.movie_mania.models.MovieDetails
import javax.inject.Inject

class MovieManiaRepository @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend fun getAllMovies(): List<MovieDetails> =
        movieDao.getAllMovies()

    suspend fun getMovie(id: String): MovieDetails? =
        movieDao.getMovie(id)

    suspend fun removeMovie(movieDetails: MovieDetails) =
        movieDao.removeMovie(movieDetails)

    suspend fun addMovie(movieDetails: MovieDetails) =
        movieDao.addMovie(movieDetails)
}