package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Room
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Rooms
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Shirt
import com.mnafis.compose_ui_android_experiment.ui.theme.Dimens
import com.mnafis.compose_ui_android_experiment.ui.theme.LightPrimaryDarkColor

@Composable
fun ShirtSelectionDialog(
    list: List<Shirt>,
    onShirtSelected: (option: Shirt) -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card {
            Column(
                modifier = Modifier.padding(Dimens.paddingMd)
            ) {
                Text(text = stringResource(id = R.string.tracker_in_the_house_shirt_change_instruction))
                list.forEach {
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = it.colorValue),
                        onClick = {
                            onShirtSelected(it)
                            onDismissRequest()
                        }
                    ) {
                        Text(text = it.colorName)
                    }
                }
            }
        }
    }
}

@Composable
fun RoomSelectionDialog(
    rooms: List<Room>,
    onRoomSelected: (option: Room) -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card {
            Column(
                modifier = Modifier.padding(Dimens.paddingMd)
            ) {
                Text(text = stringResource(id = R.string.tracker_in_the_house_room_change_instruction))
                rooms.forEach {
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = LightPrimaryDarkColor),
                        onClick = {
                            onRoomSelected(it)
                            onDismissRequest()
                        }
                    ) {
                        Text(text = it.name)
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewOptionSelectionDialog() {
    ShirtSelectionDialog(
        list = Rooms.BEDROOM.value.availableShirts,
        onShirtSelected = {},
        onDismissRequest = {}
    )
}