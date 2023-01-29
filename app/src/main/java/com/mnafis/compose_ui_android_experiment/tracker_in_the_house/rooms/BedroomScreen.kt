package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Room
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Rooms

@Composable
fun BedroomScreen(
    rooms: List<Room>,
    roomInfo: Room
) {
    CreateRoom(
        background = R.drawable.bedroom,
        rooms = rooms,
        roomInfo = roomInfo
    )
}

@Composable
@Preview
fun PreviewBedroomScreen() {
    BedroomScreen(
        rooms = Rooms.values().map { it.value },
        roomInfo = Rooms.BEDROOM.value
    )
}