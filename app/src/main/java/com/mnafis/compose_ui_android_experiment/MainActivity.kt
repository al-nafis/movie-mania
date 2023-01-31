package com.mnafis.compose_ui_android_experiment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mnafis.compose_ui_android_experiment.Experiment.*
import com.mnafis.compose_ui_android_experiment.ui.theme.ComposeUIAndroidExperimentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeUIAndroidExperimentTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = MAIN_SCREEN.name) {
                    composable(route = MAIN_SCREEN.name) {
                        MainScreen(navController)
                    }
                    composable(route = TRACKER_IN_THE_HOUSE.name) {
                        LaunchExperiment(experiment = TRACKER_IN_THE_HOUSE)
                    }
                    composable(route = MOVIE_MANIA.name) {
                        LaunchExperiment(experiment = MOVIE_MANIA)
                    }
                }
            }
        }
    }
}