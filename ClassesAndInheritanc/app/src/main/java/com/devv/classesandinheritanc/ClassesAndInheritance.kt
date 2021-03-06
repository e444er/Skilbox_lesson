package com.devv.classesandinheritanc

fun main() {
    val a = Room(area = 6.2)
    a.printDescription()

    val bedRoom = BedRoom()
    bedRoom.printDescription()

    val livingRoom = LivingRoom()
    livingRoom.printDescription()

    val kitchen = Kitchen()
    kitchen.printDescription()

    val bathRoom = BathRoom()
    bathRoom.printDescription()

    val childrenRoom = ChildrenRoom("детская комната")
    childrenRoom.printDescription()

    val roomIsFree = RoomIsFree("комната свободная")
    roomIsFree.printDescription()
}

open class Room constructor(val area: Double) {

    protected open val title: String = "Обычная комната"
    open fun printDescription() {
        println("Название комнаты: $title, Площадь комнаты: $area м. кв.")
    }
}

class BedRoom() : Room(area = 4.6) {
    override val title: String = "спальня"
    override fun printDescription() {
        println("Название комнаты: $title, Площадь комнаты: $area м. кв.")
    }
}

class LivingRoom() : Room(area = 8.6) {
    override val title: String = "гостевая"
    override fun printDescription() {
        println("Название комнаты: $title, Площадь комнаты: $area м. кв.")
    }
}

class Kitchen() : Room(area = 9.5) {
    override val title: String = "кухня"
    override fun printDescription() {
        println("Название комнаты: $title, Площадь комнаты: $area м. кв.")
    }
}

class BathRoom() : Room(area = 3.5) {
    override val title: String = "санузел"
    override fun printDescription() {
        println("Название комнаты: $title, Площадь комнаты: $area м. кв.")
    }
}


class ChildrenRoom(val titleChildrenRoom: String) : Room(area = 5.5) {
    override fun printDescription() {
        println("Название комнаты: $titleChildrenRoom, Площадь комнаты: $area м. кв.")
    }
}

class RoomIsFree(val titleRoomIsFree: String) : Room(area = 7.0) {
    override fun printDescription() {
        println("Название комнаты: $titleRoomIsFree, Площадь комнаты: $area м. кв.")
    }
}