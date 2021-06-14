package com.devv.extensions_objects_enums


fun main() {
    val virtual = Wallets.virtualWallets()
    virtual.addMoneyVirtual(Currency.RUB, 90.0)
    println(virtual)

    val realWallets = Wallets.realWallets()
    realWallets.addMoneyReal(Currency.EURO, 31, 34.0)
    println(realWallets)

}