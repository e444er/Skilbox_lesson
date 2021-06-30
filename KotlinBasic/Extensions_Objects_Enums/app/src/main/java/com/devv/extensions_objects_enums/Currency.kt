package com.devv.extensions_objects_enums

import com.devv.extensions_objects_enums.Currency.Companion.SNAKE_CASE_EURO
import com.devv.extensions_objects_enums.Currency.Companion.SNAKE_CASE_RUB
import com.devv.extensions_objects_enums.Currency.Companion.SNAKE_CASE_USD

enum class Currency {
    RUB,
    USD,
    EURO;

    companion object {
        val national: Currency = RUB
        const val SNAKE_CASE_RUB = 0.014
        const val SNAKE_CASE_USD = 1.0
        const val SNAKE_CASE_EURO = 1.2
    }
}

val Currency.toNational: Boolean
    get() = this == Currency.national


fun Currency.convertToUSD(count:Int): Double {
    return when (this) {
        Currency.RUB -> SNAKE_CASE_RUB * count
        Currency.USD -> SNAKE_CASE_USD * count
        Currency.EURO -> SNAKE_CASE_EURO * count
    }
}