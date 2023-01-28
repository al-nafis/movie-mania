package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models

data class Room(
    val name: String,
    val occupants: MutableList<Person>,
    val availableShirts: MutableList<Shirt>
)