package com.devv.BaseMethods

fun main() {
    val person1 = Person(170, 67, "Бауыржан")
    val person2 = Person(170, 67, "Бауыржан")
    val person3 = Person(180, 90, "Бэтман")
    val persons = setOf(person1, person2, person3)
    println("количество итоговых элемент ${persons.size}")
    persons.forEach { println(it.buyPet()) }
}