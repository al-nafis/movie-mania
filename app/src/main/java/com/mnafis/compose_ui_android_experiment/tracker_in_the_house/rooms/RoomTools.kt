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
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.HouseManager
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Person
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Room
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Shirt
import com.mnafis.compose_ui_android_experiment.ui.theme.Dimens
import com.mnafis.compose_ui_android_experiment.ui.theme.LightPrimaryColor
import com.mnafis.compose_ui_android_experiment.ui.theme.TextStyles

@Composable
fun CreateRoom(
    viewModel: BaseRoomViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = viewModel.background),
                contentScale = ContentScale.Crop
            )
            .padding(Dimens.paddingMd)
    ) {
        var occupantsCountMessage by remember { mutableStateOf("") }
        var occupantsCount by remember { mutableStateOf(viewModel.roomInfo.occupants.size) }
        occupantsCountMessage = stringResource(
            R.string.tracker_in_the_house_occupants_count,
            occupantsCount
        )

        Text(
            style = TextStyles.TextTitle.value,
            text = viewModel.roomInfo.name
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
            viewModel = viewModel,
            onRoomOccupantsUpdated = { occupantsCount = it }
        )
    }
}

@Composable
fun DisplayOccupants(
    viewModel: BaseRoomViewModel,
    onRoomOccupantsUpdated: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        items(viewModel.roomInfo.occupants) { person ->
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
                        room = viewModel.roomInfo,
                        person = person,
                        onShirtSelected = { viewModel.houseManager.changeShirt(person, it) }
                    )
                    RoomSelectionButton(
                        rooms = viewModel.houseManager.rooms,
                        currentRoom = viewModel.roomInfo,
                        onRoomOccupantsUpdated = onRoomOccupantsUpdated,
                        onRoomSelected = { viewModel.houseManager.moveTo(person, it) }
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
    onRoomSelected: (Room) -> Unit,
    onRoomOccupantsUpdated: (Int) -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    if (visible) {
        RoomSelectionDialog(
            rooms = rooms.filterNot { it == currentRoom },
            onRoomSelected = { selectedRoom ->
                onRoomSelected(selectedRoom)
                onRoomOccupantsUpdated(currentRoom.occupants.size)
            },
            onDismissRequest = { visible = false }
        )
    }
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = LightPrimaryColor),
        onClick = { visible = true }
    ) {
        Text(text = stringResource(id = R.string.tracker_in_the_house_change_room))
    }
}

@Composable
fun ShirtSelectionButton(
    room: Room,
    person: Person,
    onShirtSelected: (Shirt) -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    if (visible) {
        ShirtSelectionDialog(
            room.availableShirts,
            onShirtSelected = onShirtSelected,
            onDismissRequest = { visible = false }
        )
    }
    Button(
        modifier = Modifier
            .padding(end = Dimens.paddingLg),
        colors = ButtonDefaults.buttonColors(containerColor = person.shirt.colorValue),
        onClick = { visible = true }
    ) {
        Text(text = person.shirt.colorName)
    }
}

@Composable
@Preview
fun PreviewCreateRoom() {
    CreateRoom(DiningRoomViewModel(HouseManager()))
}