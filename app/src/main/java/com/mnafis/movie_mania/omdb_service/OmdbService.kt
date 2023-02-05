package com.mnafis.movie_mania.omdb_service

import com.mnafis.movie_mania.models.MovieDetails
import com.mnafis.movie_mania.models.MovieListResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface OmdbService {
    @POST("/")
    suspend fun searchByKeyWords(
        @Query("apikey") key: String,
        @Query("s") searchKey: String,
        @Query("type") type: String,
    ): Response<MovieListResponse>

    @POST("/")
    suspend fun searchByMovieDetail(
        @Query("apikey") key: String,
        @Query("i") id: String
    ): Response<MovieDetails>
}