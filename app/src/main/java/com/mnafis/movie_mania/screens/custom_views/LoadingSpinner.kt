package com.mnafis.movie_mania.screens.custom_views

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mnafis.movie_mania.theme.ColorWhite
import com.mnafis.movie_mania.theme.Dimens
import com.mnafis.movie_mania.theme.LightPrimaryColor

@Composable
fun LoadingSpinner() {
    CircularProgressIndicator(
        modifier = Modifier
            .height(Dimens.loadingSpinnerHeight)
            .width(Dimens.loadingSpinnerWidth),
        color = if (isSystemInDarkTheme()) ColorWhite else LightPrimaryColor,
    )
}