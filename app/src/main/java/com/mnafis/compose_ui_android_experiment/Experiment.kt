package com.mnafis.compose_ui_android_experiment

import androidx.compose.runtime.Composable
import com.mnafis.compose_ui_android_experiment.Experiment.TRACKER_IN_THE_HOUSE
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.HouseManager
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms.TrackerInTheHouse

enum class Experiment {
    TRACKER_IN_THE_HOUSE
}

@Composable
fun launchExperiment(experiment: Experiment) {
    when (experiment) {
        TRACKER_IN_THE_HOUSE -> TrackerInTheHouse()
        else -> TrackerInTheHouse()
    }
}