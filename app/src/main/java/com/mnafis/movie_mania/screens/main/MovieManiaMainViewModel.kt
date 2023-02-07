package com.mnafis.movie_mania.screens.main

import androidx.lifecycle.viewModelScope
import com.mnafis.movie_mania.BaseViewModel
import com.mnafis.movie_mania.models.Movie
import com.mnafis.movie_mania.models.MovieDetails
import com.mnafis.movie_mania.room_database.MovieManiaRepository
import com.mnafis.movie_mania.screens.MovieManiaScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieManiaMainViewModel @Inject constructor(
    private val repo: MovieManiaRepository
) : BaseViewModel() {

    private val movies = MutableStateFlow(emptyList<Movie>())
    private val _isDataLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isDataLoading: StateFlow<Boolean> = _isDataLoading.asStateFlow()

    override val screenName = MovieManiaScreen.MAIN_SCREEN.screenName

    fun getMovies(): StateFlow<List<Movie>> {
        viewModelScope.launch {
            movies.value = repo.getAllMovies().toMovieList()
            _isDataLoading.value = false
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
