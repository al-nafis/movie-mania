package com.mnafis.compose_ui_android_experiment.movie_mania.screens.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.mnafis.compose_ui_android_experiment.movie_mania.service.models.MovieDetails

@Composable
fun MovieManiaDetailsScreen(imdbId: String) {
    val viewModel: MovieManiaDetailsViewModel = hiltViewModel()
    val movie: MovieDetails? by viewModel.loadMovie(id = imdbId).collectAsState()
    Text(text = movie?.title ?: "")
}