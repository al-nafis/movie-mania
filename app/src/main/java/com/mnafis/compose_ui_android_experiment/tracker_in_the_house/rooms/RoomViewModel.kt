package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms

import androidx.lifecycle.ViewModel
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.HouseManager
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Room

class RoomViewModel(
    val roomInfo: Room,
    val houseManager: HouseManager
) : ViewModel()