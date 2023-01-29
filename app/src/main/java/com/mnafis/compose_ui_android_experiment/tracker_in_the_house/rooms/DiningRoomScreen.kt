package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Room
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Rooms

@Composable
fun DiningRoomScreen(
    rooms: List<Room>,
    roomInfo: Room
) {
    CreateRoom(
        background = R.drawable.dining_room,
        rooms = rooms,
        roomInfo = roomInfo
    )
}

@Composable
@Preview
fun PreviewDiningRoomScreen() {
    DiningRoomScreen(
        rooms = Rooms.values().map { it.value },
        roomInfo = Rooms.DINING_ROOM.value
    )
}