package com.mnafis.compose_ui_android_experiment.movie_mania.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.custom_views.MovieCard
import com.mnafis.compose_ui_android_experiment.movie_mania.service.models.Movie
import com.mnafis.compose_ui_android_experiment.ui.theme.ColorWhite
import com.mnafis.compose_ui_android_experiment.ui.theme.Dimens
import com.mnafis.compose_ui_android_experiment.ui.theme.LightPrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieManiaMainScreen(
    onClickNavigate: (id: String) -> Unit,
    onClickFloatingButton: () -> Unit
) {
    val viewModel: MovieManiaMainViewModel = hiltViewModel()
    val movies by viewModel.searchMovieByKeyWords("aaa").collectAsState() // todo: this will be replace by repository call

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = viewModel.screenName) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = LightPrimaryColor,
                    titleContentColor = ColorWhite
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                containerColor = LightPrimaryColor,
                onClick = onClickFloatingButton,
            ) {
                Text(
                    color = ColorWhite,
                    text = stringResource(id = R.string.movie_mania_add_movie_button)
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(
                    start = Dimens.screenPadding,
                    end = Dimens.screenPadding,
                    top = Dimens.screenPadding
                ),
            verticalArrangement = Arrangement.spacedBy(Dimens.itemPadding)
        ) {
            items(items = movies) {
                MovieCard(
                    title = it.title,
                    year = it.year,
                    imageUrl = it.poster,
                    onClick = {
                        onClickNavigate(it.imdbID)
                    }
                )
            }
            if (movies.isEmpty()) {
                item {
                    Text(text = stringResource(id = R.string.movie_mania_selected_movies_empty_list_message))
                }
            }

            // this space is to display the last list item so the floating button does not overlap on it
            item {
                Divider(
                    modifier = Modifier.height(60.dp),
                    color = Color.Transparent
                )
            }
        }
    }
}



@Composable
@Preview
fun PreviewMovieManiaMainScreen() {
    MovieManiaMainScreen({}, {})
}