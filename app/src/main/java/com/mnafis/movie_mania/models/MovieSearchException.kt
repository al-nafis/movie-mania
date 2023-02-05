package com.mnafis.movie_mania.models

data class MovieSearchException(
    val errorMessage: String
) : Exception(errorMessage)