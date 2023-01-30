package com.mnafis.compose_ui_android_experiment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.HouseManager
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.RoomName
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms.CreateRoom
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.rooms.RoomViewModel
import com.mnafis.compose_ui_android_experiment.ui.theme.ComposeUIAndroidExperimentTheme
import com.mnafis.compose_ui_android_experiment.ui.theme.Dimens
import com.mnafis.compose_ui_android_experiment.ui.theme.LightPrimaryColor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPref: SharedPreferences

    @Inject
    lateinit var houseManager: HouseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUIAndroidExperimentTheme {
                CreateRoom(
                    viewModel = RoomViewModel(
                        roomInfo = houseManager.rooms[RoomName.DINING_ROOM.value]!!,
                        houseManager = houseManager
                    )
                )
            }
        }
    }
}

@Composable
fun Greeting() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.paddingXl),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = Dimens.paddingLg),
                fontSize = Dimens.headerFontSize,
                text = "See different Experiments"
            )
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = LightPrimaryColor),
                onClick = { /*todo: add navigation to Living room */ }
            ) {
                Text(text = stringResource(id = R.string.tracker_in_the_house_title))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeUIAndroidExperimentTheme {
        Greeting()
    }
}