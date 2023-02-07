package com.mnafis.movie_mania

import com.mnafis.movie_mania.screens.MovieManiaScreen

sealed class Screen(val route: String) {
    object Main : Screen(MovieManiaScreen.MAIN_SCREEN.screenName)
    object Search : Screen(MovieManiaScreen.SEARCH_SCREEN.screenName)
    object Details : Screen(MovieManiaScreen.DETAIL_SCREEN.screenName + "/{imdb_id}") {
        const val key = "imdb_id"
        fun routeWithId(id: String) = route.replace("{imdb_id}", id)
    }
}