package com.mnafis.movie_mania.omdb_service

import android.util.Log
import com.mnafis.movie_mania.MOVIE_MANIA_LOG_KEY
import com.mnafis.movie_mania.models.Movie
import com.mnafis.movie_mania.models.MovieDetails
import com.mnafis.movie_mania.models.MovieSearchException
import javax.inject.Inject

class OmdbManager @Inject constructor(
    private val service: OmdbService
) {
    companion object {
        const val omdbBaseUrl = "https://omdbapi.com"
    }

    private val apiKey = "34737eb2"
    private val searchType = "movie"

    suspend fun searchByKeyWords(keyWords: String): List<Movie> = try {
        val response = service.searchByKeyWords(
            key = apiKey,
            searchKey = keyWords,
            type = searchType
        )
        if (response.isSuccessful) {
            response.body()?.list ?: emptyList()
        } else {
            Log.d(MOVIE_MANIA_LOG_KEY, response.errorBody().toString())
            emptyList()
        }
    } catch (e: Exception) {
        Log.d(MOVIE_MANIA_LOG_KEY, e.message.toString())
        emptyList()
    }

    suspend fun searchById(id: String): MovieDetails? = try {
        val response = service.searchByMovieDetail(
            key = apiKey,
            id = id
        )
        if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}