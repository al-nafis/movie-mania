package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Person
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Room
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
        val occupants = remember { mutableStateOf(roomInfo.occupants) }
        DisplayOccupants(list = occupants)
    }
}

@Composable
fun DisplayOccupants(list: MutableState<MutableList<Person>>) {
    for (person in list.value)
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = person.shirt.color),
            onClick = { /* todo: show options to rooms and shirts selection */ }
        ) {
            Text(text = person.name)
        }
}