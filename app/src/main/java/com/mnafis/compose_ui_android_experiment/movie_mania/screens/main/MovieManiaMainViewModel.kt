package com.mnafis.compose_ui_android_experiment.movie_mania.screens.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mnafis.compose_ui_android_experiment.movie_mania.MovieManiaBaseViewModel
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.MovieManiaScreen
import com.mnafis.compose_ui_android_experiment.movie_mania.service.MovieManiaManager
import com.mnafis.compose_ui_android_experiment.movie_mania.service.MovieSearchException
import com.mnafis.compose_ui_android_experiment.movie_mania.service.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieManiaMainViewModel @Inject constructor(
    private val movieManiaManager: MovieManiaManager
) : MovieManiaBaseViewModel() {

    private val movies = MutableStateFlow(emptyList<Movie>())
    override val screenName = MovieManiaScreen.MAIN_SCREEN.screenName

    fun searchMovieByKeyWords(keyWords: String): StateFlow<List<Movie>> {
        viewModelScope.launch {
            try {
                movies.value = movieManiaManager.searchByKeyWords(keyWords)
            } catch (e: MovieSearchException) {
                // todo: update ui to display message
                Log.d("ABID", e.errorMessage)
            }
        }
        return movies.asStateFlow()
    }
}