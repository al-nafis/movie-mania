package com.mnafis.compose_ui_android_experiment.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

enum class TextStyles(val value: TextStyle) {
    TextTitle(TextStyle(
        color = Color(Color.Black.value),
        fontSize = Dimens.titleFontSize,
    )),
    TextBody(TextStyle(
        color = Color(Color.Black.value),
        fontSize = Dimens.bodyFontSize,
    )),
    TextBodyHeaderBolded(TextStyle(
        color = Color(Color.Black.value),
        fontSize = Dimens.bodyHeaderFontSize,
        fontWeight = FontWeight.Bold
    )),
    TextBodyBolded(TextStyle(
        color = Color(Color.Black.value),
        fontSize = Dimens.bodyFontSize,
        fontWeight = FontWeight.Bold
    )),
    TextHeader(TextStyle(
        color = Color(Color.Black.value),
        fontSize = Dimens.headerFontSize,
    ))
}