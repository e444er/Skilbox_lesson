package com.devv.navviewmodel

sealed class Person {
    data class User(
        val id: Long,
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