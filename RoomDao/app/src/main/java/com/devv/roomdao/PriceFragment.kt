package com.devv.roomdao

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.devv.roomdao.db.Databasee
import com.devv.roomdao.db.Price
import kotlinx.coroutines.launch
import timber.log.Timber

class PriceFragment() : Fragment(R.layout.price_fragment) {
    private val userDao = Databasee.instance.userDao()
    private val priceDao = Databasee.instance.priceDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savePrice()
        userRelation()
    }

    private fun savePrice() {
        val price: List<Price> = listOf(
            Price(
                id = 0,
                userId = 1,
                price = 100,
                status = PriceStatus.PAYED
            ),
            Price(
                id = 0,
                userId = 1,
                price = 10,
                status = PriceStatus.FINISHED
            )
        )
        lifecycleScope.launch {
            priceDao?.insertPrice(price)
        }
    }

    private fun userRelation() {
        lifecycleScope.launch {
            val userRealtion = userDao?.getAllUserRelations()
            Timber.d(userRealtion.toString())
        }
    }
}