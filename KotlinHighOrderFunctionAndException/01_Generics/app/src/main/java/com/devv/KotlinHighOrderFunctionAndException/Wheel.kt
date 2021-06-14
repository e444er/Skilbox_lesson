package com.devv.KotlinHighOrderFunctionAndException

fun main() {
    val imit = Wheel()
    imit.check(2.0)
    println(imit)
}

class Wheel(
    private val pressure: Double = 0.0
) {
    class TooHighPressure : Throwable()
    class TooLowPressure : Throwable()
    class IncorrectPressure : Throwable()

    fun setPressure(value: Double) {
        if (value < 0.0 || value > 10.0)
            throw  IncorrectPressure()
        else
            println("давление норм $value")
    }

    fun check(value: Double) {
        when {
            value in 1.6..2.5 -> println("давление норм $value")
            value < 1.5 -> TooLowPressure()
            value > 2.6 -> TooHighPressure()
        }
    }
}