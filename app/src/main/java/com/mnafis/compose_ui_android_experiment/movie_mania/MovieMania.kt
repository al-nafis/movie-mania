package com.mnafis.compose_ui_android_experiment.movie_mania

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.MovieManiaDetailsScreen
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.MovieManiaMainScreen
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.MovieManiaScreen.*
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.MovieManiaSearchScreen
import com.mnafis.compose_ui_android_experiment.ui.theme.ComposeUIAndroidExperimentTheme

@Composable
fun MovieMania() {
    val navController = rememberNavController()
    ComposeUIAndroidExperimentTheme {
        NavHost(navController = navController, startDestination = MAIN_SCREEN.screenName) {
            composable(route = MAIN_SCREEN.screenName) {
                MovieManiaMainScreen { navController.navigate(SEARCH_SCREEN.screenName) }
            }
            composable(route = SEARCH_SCREEN.screenName) {
                MovieManiaSearchScreen { navController.navigate(DETAIL_SCREEN.screenName) }
            }
            composable(route = DETAIL_SCREEN.screenName) {
                MovieManiaDetailsScreen()
            }
        }
    }
}