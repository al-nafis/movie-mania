package com.mnafis.movie_mania.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mnafis.movie_mania.R
import com.mnafis.movie_mania.screens.custom_views.MovieCard
import com.mnafis.movie_mania.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieManiaMainScreen(
    onClickNavigate: (id: String) -> Unit,
    onClickFloatingButton: () -> Unit
) {
    val viewModel: MovieManiaMainViewModel = hiltViewModel()
    val movies by viewModel.getMovies().collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = viewModel.screenName) }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onClickFloatingButton
            ) {
                Text(
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

            // this space is to display the last list item so the floating button does not overlap on it
            item {
                Divider(
                    modifier = Modifier.height(60.dp),
                    color = Color.Transparent
                )
            }
        }
        if (movies.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimens.screenPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.movie_mania_selected_movies_empty_list_message),
                    textAlign = TextAlign.Center
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