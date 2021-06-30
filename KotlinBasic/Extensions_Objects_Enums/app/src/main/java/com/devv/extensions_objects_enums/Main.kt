package com.devv.extensions_objects_enums


fun main() {
    val virtual = Wallets.VirtualWallets()
    virtual.addMoneyVirtual(Currency.RUB, 90.0)
    println(virtual)

    val realWallets = Wallets.RealWallets()
    realWallets.addMoneyReal(Currency.EURO, 31, 34.0)
    println(realWallets)

    //не знаю что делать дальше

    println(Currency.RUB.convertToUSD(100))
    println(Currency.USD.convertToUSD(100))
    println(Currency.EURO.convertToUSD(100))

    println(Currency.USD.toNational)
    println(Currency.RUB.toNational)


}