package com.mnafis.compose_ui_android_experiment.movie_mania.models

data class MovieSearchException(
    val errorMessage: String
) : Exception(errorMessage)