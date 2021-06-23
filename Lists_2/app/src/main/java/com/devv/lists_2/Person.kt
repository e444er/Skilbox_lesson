package com.devv.lists_2

sealed class Person {
    data class User(
        val id :Long,
        val name: String,
        val avatarLink: String,
        val age: Int,
    ) : Person()

    data class Developer(
        val id: Long,
        val name: String,
        val avatarLink: String,
        val age: Int,
        val programLong: String
    ) : Person()
}