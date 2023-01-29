package com.mnafis.compose_ui_android_experiment.tracker_in_the_house

import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Person
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Room
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Rooms
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Shirt
import javax.inject.Inject

class HouseManager @Inject constructor() {

    //todo: This method will get the room info from local storage
    val rooms = Rooms.values().map { it.value }

    fun moveTo(person: Person, room: Room) {
        rooms.forEach { currentRoom ->
            if (currentRoom.occupants.any { it.name == person.name }) {
                currentRoom.occupants.remove(person)
                room.occupants.add(person)
            }
        }
    }

    fun changeShirt(person: Person, shirt: Shirt) {
        rooms.forEach { room ->
            if (room.occupants.any { it.name == person.name }) {
                room.availableShirts.add(person.shirt)
                room.availableShirts.remove(shirt)
                person.shirt = shirt
            }
        }
    }
}