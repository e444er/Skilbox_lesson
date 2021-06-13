package com.devv.ClassesAndInheritance


fun main() {
    val a = Room(area = 2.2)
    a.getDescription()
    println(a)
}

open class Room constructor(area: Double) {
    val area: Double = area
    protected open val title: String = "Обычная комната"
    final fun getDescription() {
        println("Название комнаты: $title, Площадь комнаты: $area м. кв.")
    }
}

