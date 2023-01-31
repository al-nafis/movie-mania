package com.mnafis.compose_ui_android_experiment.movie_mania

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mnafis.compose_ui_android_experiment.movie_mania.service.MovieManiaManager
import com.mnafis.compose_ui_android_experiment.movie_mania.service.MovieSearchException
import com.mnafis.compose_ui_android_experiment.movie_mania.service.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieManiaMainViewModel @Inject constructor(
    private val movieManiaManager: MovieManiaManager
) : MovieManiaBaseViewModel() {

    override val screenName = "Movie Mania"



    // todo: It either this method
    suspend fun search(keyWords: String): List<Movie> =
        movieManiaManager.searchByKeyWords(keyWords)

    // todo: or this one
    fun searchMovieByKeyWords(keyWords: String): List<Movie> {
        var list = emptyList<Movie>()
        viewModelScope.launch {
            try {
                list = movieManiaManager.searchByKeyWords(keyWords)
            } catch (e: MovieSearchException) {
                // todo: update ui to display message
                Log.d("ABID", e.errorMessage)
            }
        }
        return list
    }
}