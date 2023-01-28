package com.mnafis.compose_ui_android_experiment.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

enum class TextStyles(val value: TextStyle) {
    TextTitle(TextStyle.Default.copy(
        color = Color(Color.Black.value),
        fontSize = Dimens.titleFontSize,
    )),
    TextBody(TextStyle(
        color = Color(Color.Black.value),
        fontSize = Dimens.bodyFontSize,
    )),
    TextHeader(TextStyle(
        color = Color(Color.Black.value),
        fontSize = Dimens.headerFontSize,
    ))
}