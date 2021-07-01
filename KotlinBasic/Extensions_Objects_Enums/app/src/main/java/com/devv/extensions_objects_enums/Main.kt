package com.devv.extensions_objects_enums


fun main() {

    val r = Wallets.RealWallets().addMoneyReal(type = Currency.EURO, parOf = 200, quant = 4.5)
    val v = Wallets.VirtualWallets().addMoneyVirtual(type = Currency.USD, quant = 430.0)

    println(r)
    println(v)

    println(Currency.RUB.convertToUSD(100))
    println(Currency.USD.convertToUSD(100))
    println(Currency.EURO.convertToUSD(100))

    println(Currency.USD.toNational)
    println(Currency.RUB.toNational)


}