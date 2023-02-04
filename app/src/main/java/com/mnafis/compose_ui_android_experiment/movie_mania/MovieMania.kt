package com.mnafis.compose_ui_android_experiment.movie_mania

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.MovieManiaScreen.*
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.details.MovieManiaDetailsScreen
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.details.MovieManiaDetailsViewModel
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.main.MovieManiaMainScreen
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.search.MovieManiaSearchScreen
import com.mnafis.compose_ui_android_experiment.ui.theme.ComposeUIAndroidExperimentTheme

@Composable
fun MovieMania() {
    val navController = rememberNavController()
    ComposeUIAndroidExperimentTheme {
        NavHost(navController = navController, startDestination = MAIN_SCREEN.screenName) {
            composable(route = MAIN_SCREEN.screenName) {
                MovieManiaMainScreen(
                    onClickNavigate = {
                        navController.navigate(route = "${DETAIL_SCREEN.screenName}/$it")
                    },
                    onClickFloatingButton = { navController.navigate(SEARCH_SCREEN.screenName) }
                )
            }
            composable(route = SEARCH_SCREEN.screenName) {
                MovieManiaSearchScreen { navController.navigate(route = "${DETAIL_SCREEN.screenName}/$it") }
            }
            composable(
                route = "${DETAIL_SCREEN.screenName}/{${MovieManiaDetailsViewModel.SAVED_MOVIE_KEY}}",
                arguments = listOf(navArgument(MovieManiaDetailsViewModel.SAVED_MOVIE_KEY) {
                    type = NavType.StringType
                })
            ) {
                val id = it.arguments?.getString(MovieManiaDetailsViewModel.SAVED_MOVIE_KEY)
                MovieManiaDetailsScreen(id ?: "")
            }
        }
    }
}