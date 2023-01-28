package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models

enum class Rooms(val value: String) {
    LIVING_ROOM("Living Room"),
    DINING_ROOM("Dining Room"),
    BEDROOM("Bedroom")
}

data class Room(
    val name: String,
    val occupants: MutableList<Person>,
    val availableShirts: MutableList<Shirt>
)