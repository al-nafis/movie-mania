package com.mnafis.movie_mania.models

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("Search") val list: List<Movie>
)