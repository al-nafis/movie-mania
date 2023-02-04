package com.mnafis.compose_ui_android_experiment.movie_mania.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("Title") var title: String,
    @SerializedName("Year") var year: String,
    @SerializedName("imdbID") var imdbID: String,
    @SerializedName("Poster") var poster: String
) : Parcelable