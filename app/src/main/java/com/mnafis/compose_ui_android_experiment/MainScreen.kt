package com.mnafis.compose_ui_android_experiment

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mnafis.compose_ui_android_experiment.ui.theme.ComposeUIAndroidExperimentTheme
import com.mnafis.compose_ui_android_experiment.ui.theme.Dimens
import com.mnafis.compose_ui_android_experiment.ui.theme.LightPrimaryColor

@Composable
fun MainScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.screenPadding),
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
                text = "See Different Experiments"
            )
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = LightPrimaryColor),
                onClick = { navController.navigate(Experiment.TRACKER_IN_THE_HOUSE.name) }
            ) {
                Text(text = stringResource(id = R.string.tracker_in_the_house_title))
            }
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = LightPrimaryColor),
                onClick = { navController.navigate(Experiment.MOVIE_MANIA.name) }
            ) {
                Text(text = stringResource(id = R.string.movie_mania_title))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeUIAndroidExperimentTheme {
        MainScreen(navController = rememberNavController())
    }
}