package com.jorge.data.repository

import com.jorge.data.model.MarketPricesDataModel
import com.jorge.data.model.PriceDataModel

object TestModels {

    private val testPriceDataModel = PriceDataModel(1L, 4f)
    private val testPriceDataModel2 = PriceDataModel(16L, 14f)

    val TEST_MARKET_PRICES = MarketPricesDataModel(
        prices = listOf(testPriceDataModel, testPriceDataModel2),
        description = "test"
    )
}