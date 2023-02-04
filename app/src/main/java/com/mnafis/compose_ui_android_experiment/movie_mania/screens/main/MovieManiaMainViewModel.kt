package com.mnafis.compose_ui_android_experiment.movie_mania.screens.main

import androidx.lifecycle.viewModelScope
import com.mnafis.compose_ui_android_experiment.movie_mania.MovieManiaBaseViewModel
import com.mnafis.compose_ui_android_experiment.movie_mania.local_database.MovieManiaRepository
import com.mnafis.compose_ui_android_experiment.movie_mania.models.Movie
import com.mnafis.compose_ui_android_experiment.movie_mania.models.MovieDetails
import com.mnafis.compose_ui_android_experiment.movie_mania.models.MovieSearchException
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.MovieManiaScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieManiaMainViewModel @Inject constructor(
    private val repo: MovieManiaRepository
) : MovieManiaBaseViewModel() {

    private val movies = MutableStateFlow(emptyList<Movie>())
    override val screenName = MovieManiaScreen.MAIN_SCREEN.screenName

    fun getMovies(): StateFlow<List<Movie>> {
        viewModelScope.launch {
            try {
                movies.value = repo.getAllMovies().toMovieList()
            } catch (e: MovieSearchException) {
            }
        }
        return movies.asStateFlow()
    }
}

private fun List<MovieDetails>.toMovieList(): List<Movie> = map {
    Movie(
        title = it.title,
        year = it.year,
        imdbID = it.imdbID,
        poster = it.poster
    )
}
