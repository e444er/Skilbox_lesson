package com.devv.`02_HighOrderFunctions`

fun main() {
    var list:Queue<Int> = Queue(
        mutableListOf(1,32,43,657,87)
    )
    println(list.filter { it > 0 })

}

class Queue<T>(var list:List<T> = mutableListOf()) {

    fun filter(prediicate: (T)-> Boolean): Queue<T>{
        return Queue(list.filter(prediicate))
    }

    override fun toString(): String {
        return "Queue(list=$list)"
    }
}
