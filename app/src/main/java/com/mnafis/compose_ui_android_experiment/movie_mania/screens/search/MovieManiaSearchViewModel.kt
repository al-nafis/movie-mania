package com.mnafis.compose_ui_android_experiment.movie_mania.screens.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mnafis.compose_ui_android_experiment.movie_mania.MovieManiaBaseViewModel
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.MovieManiaScreen
import com.mnafis.compose_ui_android_experiment.movie_mania.service.MovieManiaManager
import com.mnafis.compose_ui_android_experiment.movie_mania.service.MovieSearchException
import com.mnafis.compose_ui_android_experiment.movie_mania.service.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieManiaSearchViewModel @Inject constructor(
    private val movieManiaManager: MovieManiaManager
) : MovieManiaBaseViewModel() {
    private val _movies = MutableStateFlow(emptyList<Movie>())
    val movies = _movies.asStateFlow()
    override val screenName: String = MovieManiaScreen.SEARCH_SCREEN.screenName

    fun searchMovieByKeyWords(keyWords: String) {
        if (keyWords.isEmpty()) {
            _movies.value = emptyList()
        } else {
            viewModelScope.launch { // todo: test if calling this method creates multiple scopes by logging the list with a counter
                try {
                    _movies.value = movieManiaManager.searchByKeyWords(keyWords)
                } catch (e: MovieSearchException) {
                    // todo: update ui to display message
                    Log.d("ABID", e.errorMessage)
                }
            }
        }
    }
}