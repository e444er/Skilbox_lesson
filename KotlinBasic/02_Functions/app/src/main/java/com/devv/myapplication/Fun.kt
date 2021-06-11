package com.devv.myapplication

import kotlin.math.sqrt

fun main() {
    val solutionSum = solveEquation(32, 54, 43)
    println(solutionSum)
}


fun solveEquation(a: Int, b: Int, c: Int): Double {
    val discriminant =( (b * b) - (4 * a * c).toDouble())
    val x1 = ((-b) + sqrt(discriminant)) / (2 * a)
    val x2 = ((-b) - sqrt(discriminant)) / (2 * a)

    if (discriminant <= 0) {
        println("не имеет корней")
    } else {
        println(x1)
        println(x2)
    }
    return x1 + x2
}