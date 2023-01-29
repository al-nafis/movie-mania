package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms

import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.HouseManager
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Rooms
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LivingRoomViewModel @Inject constructor(
    houseManager: HouseManager
) : BaseRoomViewModel(
    background = R.drawable.living_room,
    roomInfo = Rooms.LIVING_ROOM.value, houseManager
)