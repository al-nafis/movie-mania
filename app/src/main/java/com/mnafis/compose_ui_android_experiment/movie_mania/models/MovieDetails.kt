package com.mnafis.compose_ui_android_experiment.movie_mania.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movie_details")
data class MovieDetails(
    @SerializedName("Title") @ColumnInfo("title") val title: String,
    @SerializedName("Year") @ColumnInfo("year") val year: String,
    @SerializedName("Rated") @ColumnInfo("rated") val rated: String,
    @SerializedName("Runtime") @ColumnInfo("runtime") val runtime: String,
    @SerializedName("Genre") @ColumnInfo("genre") val genre: String,
    @SerializedName("Director") @ColumnInfo("director") val director: String,
    @SerializedName("Writer") @ColumnInfo("writer") val writer: String,
    @SerializedName("Actors") @ColumnInfo("actors") val actors: String,
    @SerializedName("Plot") @ColumnInfo("plot") val plot: String,
    @SerializedName("Language") @ColumnInfo("language") val language: String,
    @SerializedName("Country") @ColumnInfo("country") val country: String,
    @SerializedName("Awards") @ColumnInfo("awards") val awards: String,
    @SerializedName("Poster") @ColumnInfo("poster") val poster: String,
    @SerializedName("Metascore") @ColumnInfo("metascore") val metascore: String,
    @SerializedName("imdbRating") @ColumnInfo("imdbRating") val imdbRating: String,
    @SerializedName("imdbVotes") @ColumnInfo("imdbVotes") val imdbVotes: String,
    @SerializedName("imdbID") @ColumnInfo("imdbID") @PrimaryKey val imdbID: String,
    @SerializedName("BoxOffice") @ColumnInfo("boxOffice") val boxOffice: String,
) : Parcelable