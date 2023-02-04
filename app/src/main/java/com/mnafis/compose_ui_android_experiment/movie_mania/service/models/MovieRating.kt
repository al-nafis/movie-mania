package com.mnafis.compose_ui_android_experiment.movie_mania.service.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieRating(
    @SerializedName("Source") val source: String,
    @SerializedName("Value") val value: String
) : Parcelable