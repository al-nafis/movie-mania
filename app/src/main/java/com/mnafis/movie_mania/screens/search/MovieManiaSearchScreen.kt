package com.mnafis.movie_mania.screens.search

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.mnafis.movie_mania.R
import com.mnafis.movie_mania.screens.custom_views.MovieCard
import com.mnafis.movie_mania.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieManiaSearchScreen(
    onClickNavigate: (id: String) -> Unit,
    onBackPressed: () -> Unit
) {
    val viewModel: MovieManiaSearchViewModel = hiltViewModel()
    val movies by viewModel.movies.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = viewModel.screenName) },
                navigationIcon = {
                    IconButton(
                        onClick = onBackPressed
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.movie_mania_back_button_content_description)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(
                    start = Dimens.screenPadding,
                    end = Dimens.screenPadding,
                    top = Dimens.screenPadding
                )
        ) {
            SearchBar {
                viewModel.searchMovieByKeyWords(it.text)
            }

            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(Dimens.itemPadding)
            ) {
                items(items = movies) {
                    MovieCard(
                        title = it.title,
                        year = it.year,
                        imageUrl = it.poster,
                        onClick = { onClickNavigate(it.imdbID) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun SearchBar(
    onTextChanged: (TextFieldValue) -> Unit
) {
    val searchText: MutableState<TextFieldValue> =
        remember { mutableStateOf(TextFieldValue("")) }
    val keyboard = LocalSoftwareKeyboardController.current
    TextField(
        placeholder = { Text(text = stringResource(id = R.string.movie_mania_search_bar_hint)) },
        value = searchText.value,
        keyboardActions = KeyboardActions(onSearch = { keyboard?.hide() }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        onValueChange = { value ->
            searchText.value = value
            onTextChanged(value)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimens.paddingMd),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = stringResource(id = R.string.movie_mania_search_bar_search_icon_content_description),
                modifier = Modifier
            )
        },
        trailingIcon = {
            if (searchText.value != TextFieldValue("")) {
                IconButton(
                    onClick = { searchText.value = TextFieldValue("") }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.movie_mania_search_bar_close_icon_content_description),
                        modifier = Modifier
                    )
                }
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(textColor = if (isSystemInDarkTheme()) ColorOffWhite else ColorGray)
    )
}
