package com.devv.contentprovider

data class Contact(
    val id: Long,
    val name: String,
    val number: List<String>,
)