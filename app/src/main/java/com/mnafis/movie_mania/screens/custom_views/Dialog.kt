package com.mnafis.movie_mania.screens.custom_views

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mnafis.movie_mania.theme.*

@Composable
fun AppDialog(
    titleText: String,
    bodyText: String,
    primaryButtonText: String,
    onPrimaryButtonClick: () -> Unit,
    onDismiss: () -> Unit = {}
) {
    AlertDialog(
        containerColor = if (isSystemInDarkTheme()) DarkCardBackground else ColorWallWhite,
        textContentColor = if (isSystemInDarkTheme()) ColorWhite else ColorBlack,
        titleContentColor = if (isSystemInDarkTheme()) ColorWhite else ColorBlack,
        onDismissRequest = onDismiss,
        title = {
            Text(text = titleText)
        },
        text = {
            Text(text = bodyText)
        },
        confirmButton = {
            Row(
                modifier = Modifier.padding(Dimens.paddingMd),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onPrimaryButtonClick
                ) {
                    Text(text = primaryButtonText)
                }
            }
        }
    )
}