package com.devv.extensions_objects_enums


fun main() {


    val r = Wallets.RealWallets().addMoneyReal(Currency.EURO, 200, 4.5)
    val v = Wallets.VirtualWallets().addMoneyVirtual(Currency.USD, 430.0)
    //не знаю что делать дальше
    println(r)
    println(v)

    println(Currency.RUB.convertToUSD(100))
    println(Currency.USD.convertToUSD(100))
    println(Currency.EURO.convertToUSD(100))

    println(Currency.USD.toNational)
    println(Currency.RUB.toNational)


}