package com.mnafis.movie_mania.screens.details

import androidx.lifecycle.viewModelScope
import com.mnafis.movie_mania.BaseViewModel
import com.mnafis.movie_mania.models.MovieDetails
import com.mnafis.movie_mania.omdb_service.OmdbManager
import com.mnafis.movie_mania.room_database.MovieManiaRepository
import com.mnafis.movie_mania.screens.MovieManiaScreen
import com.mnafis.movie_mania.utils.NetworkConnectionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieManiaDetailsViewModel @Inject constructor(
    private val manager: OmdbManager,
    private val repo: MovieManiaRepository,
    private val connectionManager: NetworkConnectionManager
) : BaseViewModel() {

    private val movie: MutableStateFlow<MovieDetails?> = MutableStateFlow(null)
    private val _isMovieListed: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isMovieListed: StateFlow<Boolean> = _isMovieListed.asStateFlow()
    private val _isDataLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isDataLoading: StateFlow<Boolean> = _isDataLoading.asStateFlow()

    override val screenName: String = MovieManiaScreen.DETAIL_SCREEN.screenName

    fun loadMovie(id: String): StateFlow<MovieDetails?> {
        if (id.isNotEmpty()) {
            viewModelScope.launch {
                val result = repo.getMovie(id)
                if (result != null) {
                    movie.value = result
                    _isMovieListed.value = true
                } else {
                    movie.value = manager.searchById(id)
                    _isMovieListed.value = false
                }
                _isDataLoading.value = false
            }
        }

        return movie.asStateFlow()
    }

    fun addOrRemoveMovie(shouldAdd: Boolean) {
        movie.value?.let {
            viewModelScope.launch {
                if (shouldAdd) {
                    repo.addMovie(it)
                } else {
                    repo.removeMovie(it)
                }
                _isMovieListed.value = shouldAdd
            }
        }
    }

    fun isNetworkAvailable() = connectionManager.isNetworkAvailable()
}