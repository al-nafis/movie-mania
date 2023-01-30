package com.mnafis.compose_ui_android_experiment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.HouseManager
import com.mnafis.compose_ui_android_experiment.ui.theme.ComposeUIAndroidExperimentTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val MAIN_SCREEN = "main screen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeUIAndroidExperimentTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = MAIN_SCREEN) {
                    composable(route = MAIN_SCREEN) {
                        MainScreen(navController)
                    }
                    composable(route = Experiment.TRACKER_IN_THE_HOUSE.name) {
                        launchExperiment(experiment = Experiment.TRACKER_IN_THE_HOUSE)
                    }
                }
            }
        }
    }
}