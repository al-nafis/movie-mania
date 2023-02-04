package com.mnafis.compose_ui_android_experiment.movie_mania.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.MovieManiaScreen
import com.mnafis.compose_ui_android_experiment.movie_mania.service.models.MovieDetails
import com.mnafis.compose_ui_android_experiment.ui.theme.*
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun MovieManiaDetailsScreen(
    imdbId: String,
    onBackPressed: () -> Unit
) {
    val viewModel: MovieManiaDetailsViewModel = hiltViewModel()
    val movie: MovieDetails? by viewModel.loadMovie(id = imdbId).collectAsState()

    DisplayMovieDetails(
        movie = movie,
        onBackPressed = onBackPressed,
        shouldAddOrRemove = {
            viewModel.addOrRemoveMovie(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DisplayMovieDetails(
    movie: MovieDetails?,
    onBackPressed: () -> Unit,
    shouldAddOrRemove: (Boolean) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = movie?.title ?: MovieManiaScreen.DETAIL_SCREEN.screenName) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = LightPrimaryColor,
                    titleContentColor = ColorWhite
                ),
                navigationIcon = {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(contentColor = ColorWhite),
                        onClick = onBackPressed
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },
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
            movie?.let {
                val isListed: MutableStateFlow<Boolean> = MutableStateFlow(false)
                item { MovieHeaderPortion(it) }
                item {
                    val isMovieListed: Boolean by isListed.collectAsState()
                    FavoriteButton(isMovieListed) {
                        shouldAddOrRemove(!isListed.value)
                    }
                }
                item { MovieDescriptionPortion(it) }
            }
            if (movie == null) {
                item {
                    Text(text = stringResource(id = R.string.movie_mania_add_movie_details_no_response_message))
                }
            }
        }
    }
}

@Composable
private fun MovieHeaderPortion(movie: MovieDetails) {
    Row {
        Box(
            modifier = Modifier
                .fillMaxWidth(Dimens.posterImageWidth)
                .height(Dimens.posterImageHeight),
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = movie.poster,
                contentDescription = "poster",
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = Dimens.paddingMd),
            verticalArrangement = Arrangement.spacedBy(Dimens.paddingMd)
        ) {
            DisplayFieldSideBySide(
                header = stringResource(id = R.string.movie_mania_add_movie_details_country),
                value = movie.country
            )
            DisplayFieldSideBySide(
                header = stringResource(id = R.string.movie_mania_add_movie_details_language),
                value = movie.language
            )
            DisplayFieldSideBySide(
                header = stringResource(id = R.string.movie_mania_add_movie_details_imdb_rating),
                value = movie.imdbRating
            )
            DisplayFieldSideBySide(
                header = stringResource(id = R.string.movie_mania_add_movie_details_imdb_votes),
                value = movie.imdbVotes
            )
            DisplayFieldSideBySide(
                header = stringResource(id = R.string.movie_mania_add_movie_details_metascore),
                value = movie.metascore
            )
            DisplayFieldSideBySide(
                header = stringResource(id = R.string.movie_mania_add_movie_details_genre),
                value = movie.genre
            )
            DisplayFieldSideBySide(
                header = stringResource(id = R.string.movie_mania_add_movie_details_rated),
                value = movie.rated
            )
        }
    }
}

@Composable
private fun FavoriteButton(isListed: Boolean, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isListed) LightPrimaryDarkColor else LightPrimaryColor
        ),
        onClick = onClick
    ) {
        Text(
            text = stringResource(
                id = if (isListed) R.string.movie_mania_add_movie_details_button_remove
                else R.string.movie_mania_add_movie_details_button_add
            )
        )
    }
}

@Composable
private fun MovieDescriptionPortion(movie: MovieDetails) {
    DisplayFieldNextLine(
        header = stringResource(id = R.string.movie_mania_add_movie_details_year),
        value = movie.year
    )
    DisplayFieldNextLine(
        header = stringResource(id = R.string.movie_mania_add_movie_details_runtime),
        value = movie.runtime
    )
    DisplayFieldNextLine(
        header = stringResource(id = R.string.movie_mania_add_movie_details_director),
        value = movie.director
    )
    DisplayFieldNextLine(
        header = stringResource(id = R.string.movie_mania_add_movie_details_writer),
        value = movie.writer
    )
    DisplayFieldNextLine(
        header = stringResource(id = R.string.movie_mania_add_movie_details_actors),
        value = movie.actors
    )
    DisplayFieldNextLine(
        header = stringResource(id = R.string.movie_mania_add_movie_details_plot),
        value = movie.plot
    )
    DisplayFieldNextLine(
        header = stringResource(id = R.string.movie_mania_add_movie_details_box_office),
        value = movie.boxOffice
    )
    DisplayFieldNextLine(
        header = stringResource(id = R.string.movie_mania_add_movie_details_awards),
        value = movie.awards
    )
}

@Composable
private fun DisplayFieldSideBySide(
    header: String,
    value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (header.isNotEmpty()) {

            Text(text = header, style = TextStyles.TextBodyBolded.value)
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(Dimens.paddingMd),
                color = Color.Transparent
            )
        }
        Text(text = value, style = TextStyles.TextBody.value)
    }
}

@Composable
private fun DisplayFieldNextLine(
    header: String = "",
    value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.paddingLg)
    ) {
        if (header.isNotEmpty()) {
            Text(text = header, style = TextStyles.TextBodyHeaderBolded.value)
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.paddingSm),
                color = Color.Transparent
            )
        }
        Text(text = value, style = TextStyles.TextBody.value)
    }
}

@Composable
@Preview
fun DisplayMovieDetails() {
    DisplayMovieDetails(
        movie = MovieDetails(
            title = "American Beauty",
            year = "1999",
            rated = "R",
            runtime = "122 min",
            genre = "Drama",
            director = "Sam Mendes",
            writer = "Alan Ball",
            actors = "Kevin Spacey, Annette Bening, Thora Birch",
            plot = "A sexually frustrated suburban father has a mid-life crisis after becoming infatuated with his daughter's best friend.",
            language = "English",
            country = "United States",
            awards = "Won 5 Oscars. 112 wins & 102 nominations total",
            poster = "https://m.media-amazon.com/images/M/MV5BNTBmZWJkNjctNDhiNC00MGE2LWEwOTctZTk5OGVhMWMyNmVhXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg",
            metascore = "84",
            imdbRating = "8.4",
            imdbVotes = "1,160,488",
            imdbID = "tt0169547",
            boxOffice = "$130,096,601"
        ),
        onBackPressed = {},
        shouldAddOrRemove = {}
    )
}
