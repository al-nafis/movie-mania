package com.mnafis.movie_mania.screens.search

import androidx.lifecycle.viewModelScope
import com.mnafis.movie_mania.BaseViewModel
import com.mnafis.movie_mania.models.Movie
import com.mnafis.movie_mania.omdb_service.OmdbManager
import com.mnafis.movie_mania.screens.MovieManiaScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieManiaSearchViewModel @Inject constructor(
    private val movieManiaManager: OmdbManager
) : BaseViewModel() {
    private val _movies = MutableStateFlow(emptyList<Movie>())
    val movies = _movies.asStateFlow()
    override val screenName: String = MovieManiaScreen.SEARCH_SCREEN.screenName

    fun searchMovieByKeyWords(keyWords: String) {
        if (keyWords.isEmpty()) {
            _movies.value = emptyList()
        } else {
            viewModelScope.launch {
                try {
                    _movies.value = movieManiaManager.searchByKeyWords(keyWords)
                } catch (e: Exception) {}
            }
        }
    }
}