package com.mnafis.compose_ui_android_experiment.movie_mania.service

import com.mnafis.compose_ui_android_experiment.movie_mania.service.models.Movie
import javax.inject.Inject

class MovieManiaManager @Inject constructor(
    private val service: OmdbService
) {
    companion object {
        const val omdbBaseUrl = "https://omdbapi.com"
    }

    private val apiKey = "34737eb2"
    private val searchType = "movie"

    suspend fun searchByKeyWords(keyWords: String): List<Movie> {
        val response = service.searchByKeyWords(
            key = apiKey,
            searchKey = keyWords,
            type = searchType
        )
        if (response.isSuccessful) {
            return response.body()?.list ?: emptyList()
        } else {
            throw MovieSearchException(errorMessage = response.errorBody()?.toString() ?: "")
        }
    }
}

data class MovieSearchException(
    val errorMessage: String
) : Exception(errorMessage)