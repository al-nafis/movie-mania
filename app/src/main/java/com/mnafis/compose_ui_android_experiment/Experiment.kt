package com.mnafis.compose_ui_android_experiment

import androidx.compose.runtime.Composable
import com.mnafis.compose_ui_android_experiment.Experiment.MOVIE_MANIA
import com.mnafis.compose_ui_android_experiment.Experiment.TRACKER_IN_THE_HOUSE
import com.mnafis.compose_ui_android_experiment.movie_mania.screens.MovieManiaMainScreen
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms.TrackerInTheHouse

enum class Experiment {
    MAIN_SCREEN,
    TRACKER_IN_THE_HOUSE,
    MOVIE_MANIA
}

@Composable
fun LaunchExperiment(experiment: Experiment) {
    when (experiment) {
        TRACKER_IN_THE_HOUSE -> TrackerInTheHouse()
        MOVIE_MANIA -> MovieMania()
        else -> {}
    }
}