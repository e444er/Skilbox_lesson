package com.devv.a01_generics


fun main() {

    val q = Queue<Int>()
    q.enqueue(21)
    q.enqueue(321)
    q.enqueue(23)
    q.enqueue(4)
    q.enqueue(12)
    q.enqueue(3)
    q.enqueue(22)
    q.dequeue()

    println(q.filter())

}

class Queue<T> {
    val queue = mutableListOf<T>()

    fun enqueue(item: T) {
        queue.add(item)
    }

    fun dequeue(): T? {
        return if (queue.isNotEmpty())
            queue.removeAt(0) else null
    }

    fun filter(): List<T> {
        val newList = mutableListOf<T>()
        queue.forEachIndexed { index, t ->
            if (index >= queue.size / 2) newList.add(t)
        }
        return newList.toList()
    }

    override fun toString(): String {
        return "Queue$queue"
    }
}