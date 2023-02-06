package com.mnafis.movie_mania

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mnafis.movie_mania.screens.MovieManiaScreen.*
import com.mnafis.movie_mania.screens.details.MovieManiaDetailsScreen
import com.mnafis.movie_mania.screens.details.MovieManiaDetailsViewModel
import com.mnafis.movie_mania.screens.main.MovieManiaMainScreen
import com.mnafis.movie_mania.screens.search.MovieManiaSearchScreen
import com.mnafis.movie_mania.theme.MovieManiaTheme

const val MOVIE_MANIA_LOG_KEY = "Movie-Mania_Log: "

@Composable
fun MovieMania() {
    val navController = rememberNavController()
    MovieManiaTheme {
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
                MovieManiaSearchScreen(
                    onClickNavigate = { navController.navigate(route = "${DETAIL_SCREEN.screenName}/$it") },
                    onBackPressed = { navController.navigateUp() }
                )
            }
            composable(
                route = "${DETAIL_SCREEN.screenName}/{${MovieManiaDetailsViewModel.SAVED_MOVIE_KEY}}",
                arguments = listOf(navArgument(MovieManiaDetailsViewModel.SAVED_MOVIE_KEY) {
                    type = NavType.StringType
                })
            ) {
                val id = it.arguments?.getString(MovieManiaDetailsViewModel.SAVED_MOVIE_KEY)
                MovieManiaDetailsScreen(
                    imdbId = id ?: "",
                    onBackPressed = { navController.navigateUp() }
                )
            }
        }
    }
}