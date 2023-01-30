package com.mnafis.compose_ui_android_experiment.tracker_in_the_house

import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Person
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Room
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.Shirt
import com.mnafis.compose_ui_android_experiment.tracker_in_the_house.models.createRooms
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HouseManager @Inject constructor() {

    //todo: This method will get the room info from local storage
    val rooms: Map<String, Room> = createRooms()

    fun moveTo(person: Person, room: String) {
        rooms.values.forEach { currentRoom ->
            if (currentRoom.occupants.any { it.name == person.name }) {
                currentRoom.occupants.remove(person)
                rooms[room]?.occupants?.add(person)
            }
        }
    }

    fun changeShirt(person: Person, shirt: Shirt) {
        rooms.values.forEach { room ->
            if (room.occupants.any { it.name == person.name }) {
                room.availableShirts.add(person.shirt)
                room.availableShirts.remove(shirt)
                person.shirt = shirt
            }
        }
    }
}