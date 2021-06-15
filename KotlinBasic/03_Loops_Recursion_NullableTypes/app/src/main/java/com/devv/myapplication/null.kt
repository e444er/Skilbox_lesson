package com.devv.myapplication

fun main() {
    print("ведите число")
    val n: Int? = readLine()?.toIntOrNull() ?: return
    if (n != null) {
        println(Loop(n))
    } else {
        println("ввели число")
    }
}

fun Loop(n: Int) {
    var number = 0
    while (number < n) {
        println("ведите число")
        val numberString = readLine()?.toIntOrNull() ?: continue
        if (numberString != null) {
            number++
        }
    }
}