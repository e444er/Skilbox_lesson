package com.devv.extensions_objects_enums

enum class Currency {
    RUB,
    USD,
    EURO;

    companion object {
        val national: Currency = RUB
    }
}

val Currency.toNational: Boolean
    get() = this == Currency.national

object CurrencyConverter {
    val rubtoUsd = 0.014
    val usdtoUsd = 1.0
    val eurotoUsd = 1.2
}

fun Currency.convertToUSD() : Double{
    return when(this) {
        Currency.RUB -> CurrencyConverter.rubtoUsd
        Currency.USD -> CurrencyConverter.usdtoUsd
        Currency.EURO -> CurrencyConverter.eurotoUsd
    }
}