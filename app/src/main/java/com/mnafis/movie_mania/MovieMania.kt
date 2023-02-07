package com.mnafis.movie_mania

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mnafis.movie_mania.screens.MovieManiaScreen.SEARCH_SCREEN
import com.mnafis.movie_mania.screens.details.MovieManiaDetailsScreen
import com.mnafis.movie_mania.screens.main.MovieManiaMainScreen
import com.mnafis.movie_mania.screens.search.MovieManiaSearchScreen
import com.mnafis.movie_mania.theme.MovieManiaTheme

const val MOVIE_MANIA_LOG_KEY = "Movie_Mania_Log"

@Composable
fun MovieMania() {
    val navController = rememberNavController()
    MovieManiaTheme {
        NavHost(navController = navController, startDestination = Screen.Main.route) {
            composable(route = Screen.Main.route) {
                MovieManiaMainScreen(
                    onClickNavigate = {
                        navController.navigate(route = Screen.Details.routeWithId(it))
                    },
                    onClickFloatingButton = { navController.navigate(SEARCH_SCREEN.screenName) }
                )
            }
            composable(route = Screen.Search.route) {
                MovieManiaSearchScreen(
                    onClickNavigate = { navController.navigate(route = Screen.Details.routeWithId(it)) },
                    onBackPressed = { navController.navigateUp() }
                )
            }
            composable(
                route = Screen.Details.route,
                arguments = listOf(navArgument(Screen.Details.key) {
                    type = NavType.StringType
                })
            ) {
                val id = it.arguments?.getString(Screen.Details.key)
                MovieManiaDetailsScreen(
                    imdbId = id ?: "",
                    onBackPressed = { navController.navigateUp() }
                )
            }
        }
    }
}