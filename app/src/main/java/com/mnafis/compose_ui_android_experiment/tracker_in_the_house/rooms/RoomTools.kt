package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Room
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

        // TESTING
        for (i in roomInfo.occupants) {
            Log.d("ABID initial values:", "${i.name}: ${i.shirt.color.value}")
        }

        DisplayOccupants(room = roomInfo)
    }
}

@Composable
fun DisplayOccupants(room: Room) {
    for (person in room.occupants) {
        val color = remember {
            mutableStateOf(person.shirt.color)
        }
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = color.value),
            onClick = {
                /* todo: show options to rooms and shirts selection */
                person.shirt = Shirt(ColorCyan)
                color.value = ColorCyan

                // TESTING
                Log.d("ABID clicked on:", person.name)
                for (i in room.occupants) {
                    Log.d("ABID changed values:", "${i.name}: ${i.shirt.color.value}")
                }
            }
        ) {
            Text(text = person.name)
        }
    }
}