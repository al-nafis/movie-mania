package com.mnafis.compose_ui_android_experiment.movie_mania.screens.main

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.ui.theme.ColorBlack
import com.mnafis.compose_ui_android_experiment.ui.theme.ColorWhite
import com.mnafis.compose_ui_android_experiment.ui.theme.Dimens
import com.mnafis.compose_ui_android_experiment.ui.theme.LightPrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieManiaMainScreen(
    onClickNavigate: () -> Unit
) {
    val viewModel: MovieManiaMainViewModel = hiltViewModel()
    val movies by viewModel.searchMovieByKeyWords("Avengers")
        .collectAsState() // todo: this will be replace by repository call

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
                onClick = onClickNavigate,
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
                .padding(padding)
                .padding(start = Dimens.screenPadding, end = Dimens.screenPadding, top = Dimens.screenPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.itemPadding)
        ) {
            items(items = movies) {
                MovieCard(
                    title = it.title,
                    year = it.year,
                    imageUrl = it.poster,
                    onClick = onClickNavigate
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
fun MovieCard(
    title: String,
    year: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .clickable(onClick = onClick)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .fillMaxWidth(Dimens.thumbnailImageWidth)
            ) {
                AsyncImage(
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillBounds,
                    model = imageUrl,
                    contentDescription = stringResource(id = R.string.movie_mania_add_movie_card_item_image_content_description)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = Dimens.paddingMd),
                verticalArrangement = Arrangement.Center
            ) {
                Text(color = ColorBlack, text = title)
                Text(
                    color = ColorBlack,
                    text = stringResource(
                        id = R.string.movie_mania_add_movie_card_item_release_year,
                        year
                    )
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewMovieManiaMainScreen() {
    MovieManiaMainScreen {}
}

@Composable
@Preview
fun PreviewMovieCard() {
    MovieCard("Movie Name", "2023", "url") {}
}