package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms

import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.HouseManager
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Rooms
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiningRoomViewModel @Inject constructor(
    houseManager: HouseManager
) : BaseRoomViewModel(
    background = R.drawable.dining_room,
    roomInfo = Rooms.DINING_ROOM.value, houseManager
)