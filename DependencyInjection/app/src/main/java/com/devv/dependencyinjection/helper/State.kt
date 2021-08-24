package com.devv.dependencyinjection.helper

import com.devv.dependencyinjection.models.TvShowItem
import com.devv.dependencyinjection.models.TvShowResponse
import retrofit2.Response

sealed class State {
    object Loading:State()
    data class Success(val tvItem:List<TvShowItem>):State()
    data class Error(val msg:Throwable):State()
}