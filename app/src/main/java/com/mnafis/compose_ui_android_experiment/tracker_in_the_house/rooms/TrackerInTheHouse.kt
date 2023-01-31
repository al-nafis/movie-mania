package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.HouseManager
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Room
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.RoomName
import com.mnafis.compose_ui_android_experiment.ui.theme.Dimens
import com.mnafis.compose_ui_android_experiment.ui.theme.LightPrimaryColor
import com.mnafis.compose_ui_android_experiment.ui.theme.TextStyles

@Composable
fun TrackerInTheHouse(
    houseManager: HouseManager = HouseManager
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Main Door") {
        composable(route = "Main Door") {
            MainDoorScreen(
                houseManager = houseManager,
                onClickRoom = { navController.navigate(it.name) }
            )
        }
        composable(route = RoomName.LIVING_ROOM.value) {
            RoomScreen(
                viewModel = RoomViewModel(
                    houseManager = houseManager,
                    roomInfo = houseManager.rooms[RoomName.LIVING_ROOM.value]!!
                )
            )
        }
        composable(route = RoomName.DINING_ROOM.value) {
            RoomScreen(
                viewModel = RoomViewModel(
                    houseManager = houseManager,
                    roomInfo = houseManager.rooms[RoomName.DINING_ROOM.value]!!
                )
            )
        }
        composable(route = RoomName.BEDROOM.value) {
            RoomScreen(
                viewModel = RoomViewModel(
                    houseManager = houseManager,
                    roomInfo = houseManager.rooms[RoomName.BEDROOM.value]!!
                )
            )
        }
    }
}

@Composable
fun MainDoorScreen(
    houseManager: HouseManager,
    onClickRoom: (Room) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.main_door),
                contentScale = ContentScale.Crop
            )
            .padding(Dimens.paddingMd)
    ) {

        Text(
            style = TextStyles.TextTitle.value,
            text = "Welcome"
        )
        Text(
            style = TextStyles.TextHeader.value,
            text = "Choose any room to go to"
        )

        houseManager.rooms.values.forEach {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = LightPrimaryColor),
                onClick = { onClickRoom(it) }
            ) {
                Text(text = it.name)
            }
        }

        logHouseInfo(houseManager)
    }
}

private fun logHouseInfo(houseManager: HouseManager) {
    houseManager.rooms.values.forEach { room ->
        val roomName = room.name
        var people = ""
        var shirts = ""
        room.occupants.forEach { people += "${it.name} " }
        room.availableShirts.forEach { shirts += "${it.colorName} " }
        Log.d("ABID", roomName)
        Log.d("ABID", people)
        Log.d("ABID", shirts)
        Log.d("ABID", "------------------------------")
    }
}