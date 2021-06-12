package com.devv.basic

fun main() {
    println("число")
    val n:Int = readLine()?.toIntOrNull() ?: return
    println("число $n")
}