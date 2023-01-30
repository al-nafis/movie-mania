package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms

import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.HouseManager
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Room

data class RoomViewModel(
    val roomInfo: Room,
    val houseManager: HouseManager
)