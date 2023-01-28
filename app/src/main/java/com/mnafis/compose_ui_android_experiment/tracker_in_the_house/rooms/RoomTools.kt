package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Room
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Rooms
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Shirt
import com.mnafis.compose_ui_android_experiment.ui.theme.ColorCyan
import com.mnafis.compose_ui_android_experiment.ui.theme.Dimens
import com.mnafis.compose_ui_android_experiment.ui.theme.TextStyles

@Composable
fun CreateRoom(
    background: Int,
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
        val input = remember { mutableStateOf("") }
        input.value = stringResource(
            id = R.string.tracker_in_the_house_occupants_count,
            roomInfo.occupants.size
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
            text = input.value
        )

        DisplayOccupants(room = roomInfo)
    }
}

@Composable
fun DisplayOccupants(room: Room) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        item {
            for (person in room.occupants) {
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
                        val color = remember {
                            mutableStateOf(person.shirt.colorValue)
                        }
                        Button(
                            modifier = Modifier
                                .padding(end = Dimens.paddingLg),
                            colors = ButtonDefaults.buttonColors(containerColor = color.value),
                            onClick = {
                                /* todo: show options to shirts selection */
                                person.shirt = Shirt(colorName = "Cyan", colorValue = ColorCyan)
                                color.value = ColorCyan
                            }
                        ) {
                            Text(text = person.shirt.colorName)
                        }
                        Button(
                            onClick = {
                                /* todo: show options to rooms selection */
                                person.shirt = Shirt(colorName = "Cyan", colorValue = ColorCyan)
                                color.value = ColorCyan
                            }
                        ) {
                            Text(text = stringResource(id = R.string.tracker_in_the_house_change_room))
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewCreateRoom() {
    CreateRoom(background = R.drawable.bedroom, roomInfo = Rooms.BEDROOM.value)
}