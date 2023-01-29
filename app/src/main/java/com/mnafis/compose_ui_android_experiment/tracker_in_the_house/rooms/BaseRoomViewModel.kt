package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.HouseManager
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Room

abstract class BaseRoomViewModel(
    @DrawableRes val background: Int,
    val roomInfo: Room,
    val houseManager: HouseManager
) : ViewModel()