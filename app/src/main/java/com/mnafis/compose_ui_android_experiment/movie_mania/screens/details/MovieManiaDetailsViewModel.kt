package com.mnafis.compose_ui_android_experiment.movie_mania.screens.details

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mnafis.compose_ui_android_experiment.movie_mania.MovieManiaBaseViewModel
import com.mnafis.compose_ui_android_experiment.movie_mania.service.MovieManiaManager
import com.mnafis.compose_ui_android_experiment.movie_mania.service.MovieSearchException
import com.mnafis.compose_ui_android_experiment.movie_mania.service.models.Movie
import com.mnafis.compose_ui_android_experiment.movie_mania.service.models.MovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieManiaDetailsViewModel @Inject constructor(
    private val movieManiaManager: MovieManiaManager
) : MovieManiaBaseViewModel() {
    companion object {
        const val SAVED_MOVIE_KEY = "imdb_id"
    }

    private val movie: MutableStateFlow<MovieDetails?> = MutableStateFlow(null)
    override val screenName: String = ""

    fun loadMovie(id: String): StateFlow<MovieDetails?> {
        if (id.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    movie.value = movieManiaManager.searchById(id)
                } catch (e: MovieSearchException) {}
            }
        }
        return movie.asStateFlow()
    }

    fun addOrRemoveMovie(shouldAdd: Boolean) {

    }
}