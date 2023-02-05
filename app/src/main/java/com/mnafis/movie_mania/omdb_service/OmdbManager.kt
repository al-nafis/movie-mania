package com.mnafis.movie_mania.omdb_service

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

    suspend fun searchById(id: String): MovieDetails? {
        val response = service.searchByMovieDetail(
            key = apiKey,
            id = id
        )
        if (response.isSuccessful) {
            return response.body()
        } else {
            throw MovieSearchException(errorMessage = response.errorBody()?.toString() ?: "")
        }
    }
}