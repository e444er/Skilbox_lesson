package com.devv.basic

fun main() {
    val firstName: String = "Бауыржан"
    val lastName: String = "Тастанбеков"
    var height: Int = 170
    val weight: Float = 70.0f
    var isChild: Boolean = height > 150 && weight > 40
    var info: String = "Имя = ${firstName}, Фамилия = ${lastName}, " +
            "Рост = ${height}, Вес = ${weight}, Ребенок = ${isChild}"
    println(info)
            height = 50
    isChild = height > 150 && weight > 40
     info = "Имя = ${firstName}, Фамилия = ${lastName}, " +
            "Рост = ${height}, Вес = ${weight}, Ребенок = ${isChild}"
    println(info)


}