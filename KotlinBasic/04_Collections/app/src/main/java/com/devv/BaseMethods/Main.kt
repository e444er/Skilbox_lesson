package com.devv.BaseMethods

fun main() {
    val eminem = Person(173, 67, "Eminem")
    val snoopdogg = Person(193, 78, "Snoop Dogg")
    val betman = Person(188, 80, "Betman")
    val persons = setOf(eminem, snoopdogg, betman)
    println("количество итоговых элемент ${persons.size}")
    persons.forEach { println(it.buyPet()) }
}