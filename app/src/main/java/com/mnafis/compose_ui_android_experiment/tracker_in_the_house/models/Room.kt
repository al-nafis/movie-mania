package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models

import androidx.annotation.DrawableRes

data class Room(
    @DrawableRes val background: Int,
    val name: String,
    val occupants: MutableList<Person>,
    val availableShirts: MutableList<Shirt>
)