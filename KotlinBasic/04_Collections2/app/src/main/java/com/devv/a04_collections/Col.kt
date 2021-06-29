package com.devv.a04_collections

fun main() {
    println("Введите количество номеров")
    val resultFunc1 = numbers(n = readLine()!!.toInt())
    filter(resultFunc1)
    sum(resultFunc1)
    size(resultFunc1)
    map(resultFunc1)
}

fun numbers(n: Int) = mutableListOf<String>().apply {
    var newNumber: String
    var step = 0
    while (step < n) {
        newNumber = readLine().toString()
        step++
        add(newNumber)
    }
}

fun filter(resultFunc1: MutableList<String>) {
    val list2 = resultFunc1.filter {
        it.startsWith("+7")
    }
    return println("Номера, начинающиеся с +7 $list2")
}

fun sum(resultFunc1: MutableList<String>) {
    return println(
        "Сумма длин всех номеров " +
                "= ${resultFunc1.sumOf { it.length }}"
    )
}

fun size(resultFunc1: MutableList<String>) {
    val set = resultFunc1.toSet()
    return println("Количество уникальных элементов = ${set.size}")
}

fun map(resultFunc1: MutableList<String>) =
    mutableMapOf<String, String>().apply {
        val mutableMap = mutableMapOf<String, String>()
        for (key in resultFunc1) {
            println("Введитe имя с номером $key")
            val value = readLine()!!
            mutableMap[key] = value
        }
        mutableMap.forEach {
            println("Человек: ${it.value}, Номер телефона: ${it.key}")
        }
    }



