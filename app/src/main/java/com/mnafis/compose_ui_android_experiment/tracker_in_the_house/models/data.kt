package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models

import com.mnafis.compose_ui_android_experiment.ui.theme.*

enum class Shirts(val value: Shirt) {
    RED(Shirt(colorName = "Red",colorValue = ColorRed)),
    BLUE(Shirt(colorName = "Blue",colorValue = ColorBlue)),
    GREEN(Shirt(colorName = "Green",colorValue = ColorGreen)),
    YELLOW(Shirt(colorName = "Yellow",colorValue = ColorYellow)),
    GRAY(Shirt(colorName = "Gray",colorValue = ColorGray)),
    BLACK(Shirt(colorName = "Black",colorValue = ColorBlack)),
    DARK_GRAY(Shirt(colorName = "Dark Gray",colorValue = ColorDarkGray)),
    MAGENTA(Shirt(colorName = "Magenta",colorValue = ColorMagenta)),
    CYAN(Shirt(colorName = "Cyan",colorValue = ColorCyan))
}

enum class HouseholdResidentsList(val value: Person) {
    ADAM(Person(name = "Adam", shirt = Shirts.RED.value)),
    BROOKE(Person(name = "Brooke", shirt = Shirts.BLUE.value)),
    CONNOR(Person(name = "Connor", shirt = Shirts.GREEN.value)),
    DELILAH(Person(name = "Delilah", shirt = Shirts.YELLOW.value)),
    ETHAN(Person(name = "Ethan", shirt = Shirts.GRAY.value))
}

enum class Rooms(val value: Room) {
    LIVING_ROOM(
        Room(
            name = "Living Room",
            occupants = mutableListOf(
                HouseholdResidentsList.ADAM.value,
                HouseholdResidentsList.ETHAN.value
            ),
            availableShirts = mutableListOf(
                Shirts.BLACK.value
            )
        )
    ),
    DINING_ROOM(
        Room(
            name = "Dining Room",
            occupants = mutableListOf(
                HouseholdResidentsList.BROOKE.value
            ),
            availableShirts = mutableListOf(
                Shirts.DARK_GRAY.value
            )
        )
    ),
    BEDROOM(
        Room(
            name = "Bedroom",
            occupants = mutableListOf(
                HouseholdResidentsList.CONNOR.value,
                HouseholdResidentsList.DELILAH.value
            ),
            availableShirts = mutableListOf(
                Shirts.MAGENTA.value,
                Shirts.CYAN.value
            )
        )
    )
}
