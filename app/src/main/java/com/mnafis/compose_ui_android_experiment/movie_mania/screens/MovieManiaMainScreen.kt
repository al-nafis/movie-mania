package com.mnafis.compose_ui_android_experiment.movie_mania.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mnafis.compose_ui_android_experiment.movie_mania.service.models.Movie
import com.mnafis.compose_ui_android_experiment.ui.theme.ColorWhite
import com.mnafis.compose_ui_android_experiment.ui.theme.ComposeUIAndroidExperimentTheme
import com.mnafis.compose_ui_android_experiment.ui.theme.Dimens
import com.mnafis.compose_ui_android_experiment.ui.theme.LightPrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieManiaMainScreen(
    onClickNavigate: () -> Unit
) {
    val viewModel: MovieManiaMainViewModel = hiltViewModel()
    val movieList = remember { mutableStateOf<List<Movie>>(emptyList()) }

    ComposeUIAndroidExperimentTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.screenPadding),
            color = MaterialTheme.colorScheme.background,
        ) {
            Scaffold(
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        containerColor = LightPrimaryColor,
                        onClick = onClickNavigate,
                    ) {
                        Text(color = ColorWhite, text = "Add Movie")
                    }
                }
            ) { padding ->
                LazyColumn(modifier = Modifier.padding(padding)) {
                    item {
                        Text(text = viewModel.screenName)
                    }

                    viewModel.searchMovieByKeyWords(
                        "Avengers",
                        movieList
                    ) // todo: this will be replace by repository call

                    items(items = movieList.value) {
                        Text(text = it.title)
                    }
                    if (movieList.value.isEmpty()) {
                        item {
                            Text(text = "No Movies on the list. Click on the Add Movie button to add a movie")
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewMovieManiaMainScreen() {
    MovieManiaMainScreen {}
}