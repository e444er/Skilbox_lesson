package com.devv.a01_generics

fun main() {
    val list:List<Number> = mutableListOf(1,3,32,34,12,22,333,54,43,67,85,354,34)
    listFilter(list)

}

fun <T: Number> listFilter(list: List<T>){
    val intNumbers = mutableListOf<T>()
    val realNumbers = mutableListOf<T>()
    for (element in list){
        if (element.toInt() % 2 == 0)
            intNumbers.add(element)
        else
            realNumbers.add(element)
    }
    println(intNumbers)
    println(realNumbers)
}