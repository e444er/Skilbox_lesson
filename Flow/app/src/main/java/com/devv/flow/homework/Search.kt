package com.devv.flow.homework

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "model.db")
data class Search(
    @PrimaryKey
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
)