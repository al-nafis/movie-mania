package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models

import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Rooms.*
import com.mnafis.compose_ui_android_experiment.ui.theme.*

val shirts = listOf(
    Shirt(color = ColorRed),
    Shirt(color = ColorBlue),
    Shirt(color = ColorGreen),
    Shirt(color = ColorYellow),
    Shirt(color = ColorGray),
    Shirt(color = ColorBlack),
    Shirt(color = ColorDarkGray),
    Shirt(color = ColorMagenta),
    Shirt(color = ColorCyan)
)

val householdResidentsList = listOf(
    Person(name = "Adam", shirt = shirts[0]),
    Person(name = "Brook", shirt = shirts[1]),
    Person(name = "Connor", shirt = shirts[2]),
    Person(name = "Delilah", shirt = shirts[3]),
    Person(name = "Ethan", shirt = shirts[4])
)

val rooms = mapOf(
    LIVING_ROOM to Room(
        name = LIVING_ROOM.value,
        occupants = mutableListOf(
            householdResidentsList[0],
            householdResidentsList[4]
        ),
        availableShirts = mutableListOf(
            shirts[5]
        )
    ),
    DINING_ROOM to Room(
        name = DINING_ROOM.value,
        occupants = mutableListOf(
            householdResidentsList[1]
        ),
        availableShirts = mutableListOf(
            shirts[6]
        )
    ),
    BEDROOM to Room(
        name = BEDROOM.value,
        occupants = mutableListOf(
            householdResidentsList[2],
            householdResidentsList[3]
        ),
        availableShirts = mutableListOf(
            shirts[7],
            shirts[8]
        )
    )
)

