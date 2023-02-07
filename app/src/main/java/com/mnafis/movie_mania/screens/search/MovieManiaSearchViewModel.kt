package com.mnafis.movie_mania.screens.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mnafis.movie_mania.BaseViewModel
import com.mnafis.movie_mania.MOVIE_MANIA_LOG_KEY
import com.mnafis.movie_mania.models.Movie
import com.mnafis.movie_mania.omdb_service.OmdbManager
import com.mnafis.movie_mania.screens.MovieManiaScreen
import com.mnafis.movie_mania.utils.NetworkConnectionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieManiaSearchViewModel @Inject constructor(
    private val manager: OmdbManager,
    private val connectionManager: NetworkConnectionManager
) : BaseViewModel() {

    private val _movies = MutableStateFlow(emptyList<Movie>())
    val movies = _movies.asStateFlow()

    private var isFirstTimeShowingNoConnectionDialog = true
    private val _showNoConnectivityMessage = MutableStateFlow(false)
    val showNoConnectivityMessage = _showNoConnectivityMessage.asStateFlow()

    override val screenName: String = MovieManiaScreen.SEARCH_SCREEN.screenName

    fun searchMovieByKeyWords(keyWords: String) {
        if (keyWords.isEmpty()) {
            _movies.value = emptyList()
        } else {
            viewModelScope.launch {
                _movies.value = manager.searchByKeyWords(keyWords)
                if (connectionManager.isNetworkAvailable()) {
                    Log.d(MOVIE_MANIA_LOG_KEY, "Internet working")
                    _showNoConnectivityMessage.value = false
                } else if (isFirstTimeShowingNoConnectionDialog) {
                    Log.d(MOVIE_MANIA_LOG_KEY, "No Internet: Showing Dialog")
                    _showNoConnectivityMessage.value = true
                    isFirstTimeShowingNoConnectionDialog = false
                }
            }
        }
    }

    fun closeDialog() {
        _showNoConnectivityMessage.value = false
    }
}