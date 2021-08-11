package com.devv.flow.homework

sealed class Resull {
    object Loading:Resull()
    data class Success(val search: List<Search>): Resull()
    data class Error(val msg:Throwable): Resull()
    object Empty:Resull()
}