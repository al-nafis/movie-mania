package com.mnafis.compose_ui_android_experiment.movie_mania.screens.details

import androidx.lifecycle.viewModelScope
import com.mnafis.compose_ui_android_experiment.movie_mania.MovieManiaBaseViewModel
import com.mnafis.compose_ui_android_experiment.movie_mania.local_database.MovieManiaRepository
import com.mnafis.compose_ui_android_experiment.movie_mania.models.MovieDetails
import com.mnafis.compose_ui_android_experiment.movie_mania.models.MovieSearchException
import com.mnafis.compose_ui_android_experiment.movie_mania.service.MovieManiaManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieManiaDetailsViewModel @Inject constructor(
    private val manager: MovieManiaManager,
    private val repo: MovieManiaRepository
) : MovieManiaBaseViewModel() {
    companion object {
        const val SAVED_MOVIE_KEY = "imdb_id"
    }

    private val movie: MutableStateFlow<MovieDetails?> = MutableStateFlow(null)
    private val _isMovieListed: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isMovieListed: StateFlow<Boolean> = _isMovieListed.asStateFlow()

    override val screenName: String = ""

    fun loadMovie(id: String): StateFlow<MovieDetails?> {
        if (id.isNotEmpty()) {
            viewModelScope.launch {
                val result = repo.getMovie(id)
                if (result != null){
                    movie.value = result
                    _isMovieListed.value = true
                } else {
                    try {
                        movie.value = manager.searchById(id)
                        _isMovieListed.value = false
                    } catch (e: MovieSearchException) {}
                }
            }
        }

        return movie.asStateFlow()
    }

    fun addOrRemoveMovie(shouldAdd: Boolean) {
        viewModelScope.launch {
            if (shouldAdd) {
                repo.addMovie(movie.value!!)
                _isMovieListed.value = true
            } else {
                repo.removeMovie(movie.value!!)
                _isMovieListed.value = false
            }
        }
    }
}