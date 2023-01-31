package com.mnafis.compose_ui_android_experiment.movie_mania

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mnafis.compose_ui_android_experiment.ui.theme.ComposeUIAndroidExperimentTheme
import com.mnafis.compose_ui_android_experiment.ui.theme.Dimens
import kotlinx.coroutines.launch

@Composable
fun MovieManiaMainScreen() {
    val viewModel: MovieManiaMainViewModel = hiltViewModel()
    val composableScope = rememberCoroutineScope()

    ComposeUIAndroidExperimentTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.screenPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn {
                item {
                    Text(text = viewModel.screenName)
                }
                composableScope.launch {
                    val result = viewModel.search("Avengers")
                    if (result.isEmpty()) Log.d("ABID", "Empty List")
                    result.forEach {
                        Log.d("ABID", it.title)
                    }
                    // todo: This is not working
                    items(items = result) {
                        Text(text = it.title)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewMovieManiaMainScreen() {
    MovieManiaMainScreen()
}