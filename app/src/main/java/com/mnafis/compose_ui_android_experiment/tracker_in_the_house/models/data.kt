package com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models

import android.util.Log
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
    val shirts = getShirts()
    val shirtsQueue: Queue<Shirt> = LinkedList(shirts).apply { shuffle() }
    val people = mutableListOf<Person>()
    residents.forEach { person ->
        shirtsQueue.poll()?.let { people.add(Person(name = person, shirt = it)) }
    }
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

private fun getRandomNumber(limit: Int) = (0 until limit).random()

private fun assignPeopleAndShirts(
    people: MutableList<Person>,
    shirtsQueue: Queue<Shirt>
): Map<String, Room> {
    val tempList = getTempRoomList()

    val rooms = mutableMapOf<String, Room>()
    val peopleQueue: Queue<Person> = LinkedList(people)
    while (peopleQueue.isNotEmpty()) {
        val ran = getRandomNumber(tempList.size)
        Log.d("ABID", "room -- $ran")
        peopleQueue.poll()?.let { tempList[ran].occupants.add(it) }
    }
    Log.d("ABID", ".......................")
    while (shirtsQueue.isNotEmpty()) {
        val ran = getRandomNumber(tempList.size)
        Log.d("ABID", "shirt -- $ran")
        shirtsQueue.poll()?.let { tempList[ran].availableShirts.add(it) }
    }
    Log.d("ABID", ".......................")

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
