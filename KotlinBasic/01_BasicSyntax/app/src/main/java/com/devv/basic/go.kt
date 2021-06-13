package com.devv.basic

fun main() {
    val firstName = "Бауыржан"
    val lastName = "Тастанбеков"
    var height = 170
    val weight = 70.0f
    var isChild = height > 150 && weight > 40
    var info = "Имя = $firstName, Фамилия = $lastName, " +
            "Рост = $height, Вес = $weight, Ребенок = $isChild"
    println(info)
    height = 50
    isChild = height > 150 && weight > 40
    info = "Имя = $firstName, Фамилия = $lastName, " +
            "Рост = $height, Вес = $weight, Ребенок = $isChild"
    println(info)
}