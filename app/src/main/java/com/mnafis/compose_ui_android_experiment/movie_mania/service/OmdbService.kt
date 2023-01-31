package com.mnafis.compose_ui_android_experiment.movie_mania.service

import com.mnafis.compose_ui_android_experiment.movie_mania.service.models.MovieListResponse
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
        @Query("t") title: String,
        @Query("y") year: String,
        @Query("type") type: String,
    ): Response<MovieListResponse>
}