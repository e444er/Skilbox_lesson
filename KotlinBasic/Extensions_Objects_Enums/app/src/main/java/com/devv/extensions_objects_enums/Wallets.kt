package com.devv.extensions_objects_enums

import com.devv.extensions_objects_enums.Currency.*

sealed class Wallets() {

    abstract fun moneyInUSD(): Double

    class virtualWallets : Wallets() {
        private var quantityRub = 0.0
        private var quantityUsd = 0.0
        private var quantityEuro = 0.0


        fun addMoneyVirtual(
            type: Currency,
            quant: Double
        ) {
            when (type) {
                RUB -> quantityRub += quant
                EURO -> quantityEuro += quant
                USD -> quantityUsd += quant
            }
        }

        override fun moneyInUSD(): Double {
            val sunvirtual = ((EURO.convertToUSD() * (quantityEuro)) +
                    RUB.convertToUSD() * (quantityRub) +
                    USD.convertToUSD() * (quantityRub))
            println("sum = $sunvirtual")
            return sunvirtual
        }
    }


    class realWallets(
    ) : Wallets() {
        private var quantityRub = 0.0
        private var quantityUsd = 0.0
        private var quantityEuro = 0.0
        private var realRub: MutableMap<Int, Double> = mutableMapOf()
        private var realUsd: MutableMap<Int, Double> = mutableMapOf()
        private var realEuro: MutableMap<Int, Double> = mutableMapOf()


        fun addMoneyReal(
            type: Currency,
            parOf: Int,
            quant: Double
        ): Double? {
           return when (type) {
                RUB -> realRub.put(parOf, quant)
                EURO -> realEuro.put(parOf, quant)
                USD -> realUsd.put(parOf, quant)
            }
        }

        override fun moneyInUSD(): Double {
           return when(RUB) {
                RUB -> quantityRub * CurrencyConverter.rubtoUsd
                USD -> quantityUsd * CurrencyConverter.usdtoUsd
                EURO -> quantityEuro * CurrencyConverter.eurotoUsd
           }

        }

    }

}
