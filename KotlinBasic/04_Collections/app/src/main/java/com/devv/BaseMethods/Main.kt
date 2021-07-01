package com.devv.BaseMethods

fun main() {
    val person1 = Person(height = 173, weight = 67, name = "Eminem")
    val person2 = Person(height = 170, weight = 67, name = "Бауыржан")
    val people = mutableSetOf(person1, person2)
    println(people.count())
    val person3 = Person(height = 180, weight = 90, name = "Бэтман")
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