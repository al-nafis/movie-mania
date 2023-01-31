package com.mnafis.compose_ui_android_experiment.movie_mania.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.viewModelScope
import com.mnafis.compose_ui_android_experiment.movie_mania.MovieManiaBaseViewModel
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

    fun searchMovieByKeyWords(keyWords: String, state: MutableState<List<Movie>>) {
        viewModelScope.launch {
            try {
                state.value = movieManiaManager.searchByKeyWords(keyWords)
            } catch (e: MovieSearchException) {
                // todo: update ui to display message
                Log.d("ABID", e.errorMessage)
            }
        }
    }
}