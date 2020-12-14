package com.jorge.marketchart.mapper

import com.jorge.domain.model.MarketPricesDomainModel
import com.jorge.domain.model.PriceDomainModel
import com.jorge.marketchart.model.MarketPricesModel
import com.jorge.marketchart.model.PriceModel

object TestObjects {

    private val testPriceDomainModel = PriceDomainModel(1L, 4f)
    private val testPriceDomainModel2 = PriceDomainModel(16L, 14f)

    private val testPriceModel = PriceModel(1L, 4f)
    private val testPriceModel2 = PriceModel(16L, 14f)

    val givenAndExpectedPrices: List<Array<Any>> = listOf(
        arrayOf(
            MarketPricesDomainModel(prices = listOf(testPriceDomainModel, testPriceDomainModel2), description = "test"),
            MarketPricesModel(prices = listOf(testPriceModel, testPriceModel2), description = "test")
        )
    )
}