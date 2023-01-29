package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Person
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Room
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Rooms
import com.mnafis.compose_ui_android_experiment.ui.theme.Dimens
import com.mnafis.compose_ui_android_experiment.ui.theme.LightPrimaryColor
import com.mnafis.compose_ui_android_experiment.ui.theme.TextStyles

@Composable
fun CreateRoom(
    background: Int,
    rooms: List<Room>,
    roomInfo: Room
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = background),
                contentScale = ContentScale.Crop
            )
            .padding(Dimens.paddingMd)
    ) {
        var occupantsCountMessage by remember { mutableStateOf("") }
        val occupantsCount = remember { mutableStateOf(roomInfo.occupants.size) }

        occupantsCountMessage = stringResource(
            R.string.tracker_in_the_house_occupants_count,
            occupantsCount.value
        )
        Text(
            style = TextStyles.TextTitle.value,
            text = roomInfo.name
        )
        Text(
            style = TextStyles.TextHeader.value,
            text = stringResource(id = R.string.tracker_in_the_house_instruction)
        )
        Text(
            style = TextStyles.TextBody.value,
            text = occupantsCountMessage
        )

        DisplayOccupants(
            rooms = rooms,
            room = roomInfo,
            totalOccupants = occupantsCount
        )
    }
}

@Composable
fun DisplayOccupants(
    rooms: List<Room>,
    room: Room,
    totalOccupants: MutableState<Int>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        items(room.occupants) { person ->
            val peopleInCurrentRoom = remember { mutableStateOf(room.occupants) }
            Column(
                modifier = Modifier
                    .padding(vertical = Dimens.paddingXXl)
            ) {
                Text(
                    style = TextStyles.TextHeader.value,
                    modifier = Modifier
                        .padding(vertical = Dimens.paddingMd),
                    text = person.name
                )
                Row(
                    modifier = Modifier
                ) {
                    ShirtSelectionButton(
                        room = room,
                        person = person,
                        peopleInCurrentRoom = peopleInCurrentRoom
                    )
                    RoomSelectionButton(
                        rooms = rooms,
                        currentRoom = room,
                        totalOccupants = totalOccupants,
                        person = person,
                        peopleInCurrentRoom = peopleInCurrentRoom
                    )
                }
            }
        }
    }
}

@Composable
fun RoomSelectionButton(
    rooms: List<Room>,
    currentRoom: Room,
    person: Person,
    peopleInCurrentRoom: MutableState<MutableList<Person>>,
    totalOccupants: MutableState<Int>
) {
    var roomSelected by remember { mutableStateOf(false) }
    if (roomSelected) {
        RoomSelectionDialog(
            currentRoom = currentRoom,
            rooms = rooms,
            onRoomSelected = { selectedRoom ->
                currentRoom.occupants.remove(person)
                selectedRoom.occupants.add(person)
                totalOccupants.value = currentRoom.occupants.size
                peopleInCurrentRoom.value = currentRoom.occupants
            },
            onDismissRequest = { roomSelected = false }
        )
    }
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = LightPrimaryColor),
        onClick = { roomSelected = true }
    ) {
        Text(text = stringResource(id = R.string.tracker_in_the_house_change_room))
    }
}

@Composable
fun ShirtSelectionButton(
    room: Room,
    person: Person,
    peopleInCurrentRoom: MutableState<MutableList<Person>>
) {
    var shirtSelected by remember { mutableStateOf(false) }

    if (shirtSelected) {
        ShirtSelectionDialog(
            room.availableShirts,
            onShirtSelected = {
                room.availableShirts.add(person.shirt)
                room.availableShirts.remove(it)
                person.shirt = it
                peopleInCurrentRoom.value = room.occupants
            },
            onDismissRequest = { shirtSelected = false }
        )
    }
    Button(
        modifier = Modifier
            .padding(end = Dimens.paddingLg),
        colors = ButtonDefaults.buttonColors(containerColor = person.shirt.colorValue),
        onClick = { shirtSelected = true }
    ) {
        Text(text = person.shirt.colorName)
    }
}

@Composable
@Preview
fun PreviewCreateRoom() {
    CreateRoom(
        rooms = Rooms.values().map { it.value },
        background = R.drawable.bedroom,
        roomInfo = Rooms.BEDROOM.value
    )
}