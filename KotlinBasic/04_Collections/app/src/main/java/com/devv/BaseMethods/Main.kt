package com.devv.BaseMethods

fun main() {
    val person1 = Person(173, 67, "Eminem")
    val person2 = Person(170, 67, "Бауыржан")
    val people = mutableSetOf(person1, person2)
    println(people.count())
    val person3 = Person(180, 90, "Бэтман")
    people.add(person3)
    people.forEach() {
        println(it.buyPet())
    }
    println(person3)
    println(person2)
    println(person1)
    println("количество итоговых элемент ${people.size}")
}
//Если есть ошибка поподробнее объясните