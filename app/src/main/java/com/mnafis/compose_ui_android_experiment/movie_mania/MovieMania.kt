package com.mnafis.compose_ui_android_experiment.movie_mania

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.MovieManiaDetailsScreen
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.MovieManiaMainScreen
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.MovieManiaScreen.*
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.MovieManiaSearchScreen

@Composable
fun MovieMania() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MAIN_SCREEN.name) {
        composable(route = MAIN_SCREEN.name) {
            MovieManiaMainScreen { navController.navigate(SEARCH_SCREEN.name) }
        }
        composable(route = SEARCH_SCREEN.name) {
            MovieManiaSearchScreen { navController.navigate(DETAIL_SCREEN.name) }
        }
        composable(route = DETAIL_SCREEN.name) {
            MovieManiaDetailsScreen()
        }
    }
}