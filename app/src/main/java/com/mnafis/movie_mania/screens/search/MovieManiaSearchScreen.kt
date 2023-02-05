package com.mnafis.movie_mania.screens.search

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.mnafis.movie_mania.R
import com.mnafis.movie_mania.screens.custom_views.MovieCard
import com.mnafis.movie_mania.theme.ColorWhite
import com.mnafis.movie_mania.theme.Dimens
import com.mnafis.movie_mania.theme.LightPrimaryColor

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
    val state: MutableState<TextFieldValue> =
        remember { mutableStateOf(TextFieldValue("")) }
    val keyboard = LocalSoftwareKeyboardController.current
    TextField(
        placeholder = { Text(text = "Search") },
        value = state.value,
        keyboardActions = KeyboardActions(onSearch = { keyboard?.hide() }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        onValueChange = { value ->
            state.value = value
            onTextChanged(value)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimens.paddingMd),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search Icon",
                modifier = Modifier
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = { state.value = TextFieldValue("") }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                    )
                }
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}
