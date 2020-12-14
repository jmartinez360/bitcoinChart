package com.jorge.data.mapper

import com.jorge.data.model.DataResult
import com.jorge.data.model.MarketPricesDataModel
import com.jorge.data.model.PriceDataModel
import com.jorge.domain.model.DomainResult
import com.jorge.domain.model.MarketPricesDomainModel
import com.jorge.domain.model.PriceDomainModel
import java.io.IOException

object TestModels {

    private val testPriceDataModel = PriceDataModel(1L, 4f)
    private val testPriceDataModel2 = PriceDataModel(16L, 14f)

    private val testPriceDomainModel = PriceDomainModel(1L, 4f)
    private val testPriceDomainModel2 = PriceDomainModel(16L, 14f)

    private val apiException = IOException("test data error")

    val givenAndExpectedPrices: List<Array<Any>> = listOf(
        arrayOf(
            DataResult.Success(
                MarketPricesDataModel(
                    prices = listOf(
                        testPriceDataModel,
                        testPriceDataModel2
                    ), description = "test"
                )
            ),
            DomainResult.Success(
                MarketPricesDomainModel(
                    prices = listOf(
                        testPriceDomainModel,
                        testPriceDomainModel2
                    ), description = "test"
                )
            )
        ),
        arrayOf(
            DataResult.Error(apiException),
            DomainResult.Error(apiException)
        )
    )
}