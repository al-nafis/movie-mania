package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models

import com.mnafis.compose_ui_android_experiment.R
import com.mnafis.compose_ui_android_experiment.ui.theme.*
import java.util.*
import kotlin.random.Random


enum class RoomName(val value: String, val background: Int) {
    LIVING_ROOM("Living Room", R.drawable.living_room),
    DINING_ROOM("Dining Room", R.drawable.dining_room),
    BEDROOM("Bedroom", R.drawable.bedroom)
}

private val residents = mutableListOf(
    "Adam",
    "Brooke",
    "Connor",
    "Delilah",
    "Ethan"
)

fun createRooms(): Map<String, Room> {
    val shirts = getShirts().apply { shuffle() }
    val shirtsQueue: Queue<Shirt> = LinkedList(shirts)
    val people = mutableListOf<Person>()
    residents.forEach { person ->
        shirtsQueue.poll()?.let { people.add(Person(name = person, shirt = it)) }
    }
    people.shuffle()
    return assignPeopleAndShirts(people, shirtsQueue)
}

private fun getShirts() = mutableListOf(
    Shirt(colorName = "Red", colorValue = ColorRed),
    Shirt(colorName = "Blue", colorValue = ColorBlue),
    Shirt(colorName = "Green", colorValue = ColorGreen),
    Shirt(colorName = "Yellow", colorValue = ColorYellow),
    Shirt(colorName = "Gray", colorValue = ColorGray),
    Shirt(colorName = "Black", colorValue = ColorBlack),
    Shirt(colorName = "Dark Gray", colorValue = ColorDarkGray),
    Shirt(colorName = "Magenta", colorValue = ColorMagenta),
    Shirt(colorName = "Cyan", colorValue = ColorCyan)
)

private fun getRandomNumber(limit: Int) = Random.nextInt(0, limit)

private fun assignPeopleAndShirts(
    people: MutableList<Person>,
    shirtsQueue: Queue<Shirt>
): Map<String, Room> {
    val tempList = getTempRoomList()

    val rooms = mutableMapOf<String, Room>()
    val peopleQueue: Queue<Person> = LinkedList(people)
    while (peopleQueue.isNotEmpty()) {
        peopleQueue.poll()?.let { tempList[getRandomNumber(tempList.size)].occupants.add(it) }
    }
    while (shirtsQueue.isNotEmpty()) {
        shirtsQueue.poll()?.let { tempList[getRandomNumber(tempList.size)].availableShirts.add(it) }
    }

    tempList.forEach { rooms[it.name] = it }
    return rooms
}

private fun getTempRoomList() = mutableListOf<Room>().apply {
    for (room in RoomName.values()) {
        add(
            Room(
                background = room.background,
                name = room.value,
                occupants = mutableListOf(),
                availableShirts = mutableListOf()
            )
        )
    }
}
