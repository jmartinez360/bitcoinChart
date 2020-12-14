package com.jorge.marketchart.domain

import com.jorge.domain.model.MarketPricesDomainModel
import com.jorge.domain.model.PriceDomainModel

object TestModels {

    private val testPriceDataModel = PriceDomainModel(1L, 4f)
    private val testPriceDataModel2 = PriceDomainModel(16L, 14f)

    val TEST_MARKET_PRICES = MarketPricesDomainModel(
        prices = listOf(testPriceDataModel, testPriceDataModel2),
        description = "Testing data"
    )
}