package com.devv.extensions_objects_enums

import com.devv.extensions_objects_enums.Currency.*
import com.devv.extensions_objects_enums.Currency.Companion.SNAKE_CASE_EURO
import com.devv.extensions_objects_enums.Currency.Companion.SNAKE_CASE_RUB
import com.devv.extensions_objects_enums.Currency.Companion.SNAKE_CASE_USD

sealed class Wallets() {

    abstract fun moneyInUSD(): Double

    class VirtualWallets : Wallets() {
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
            val sunvirtual = ((EURO.convertToUSD(0) * (quantityEuro)) +
                    RUB.convertToUSD(0) * (quantityRub) +
                    USD.convertToUSD(0) * (quantityRub))
            println("sum = $sunvirtual")
            return sunvirtual
        }
    }


    class RealWallets(
    ) : Wallets() {
        private var quantityRub = 0.0
        private var quantityUsd = 0.0
        private var quantityEuro = 0.0
        private var realRub = mutableMapOf<Int, Double>()
        private var realUsd = mutableMapOf<Int, Double>()
        private var realEuro = mutableMapOf<Int, Double>()


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
            return when (RUB) {
                RUB -> quantityRub * SNAKE_CASE_RUB
                USD -> quantityUsd * SNAKE_CASE_USD
                EURO -> quantityEuro * SNAKE_CASE_EURO
            }

        }

    }

}
