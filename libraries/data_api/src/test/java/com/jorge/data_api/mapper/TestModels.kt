package com.jorge.data_api.mapper

import com.jorge.data.model.DataResult
import com.jorge.data.model.MarketPricesDataModel
import com.jorge.data.model.PriceDataModel
import com.jorge.data_api.model.ApiResult
import com.jorge.data_api.model.MarketPricesApiModel
import com.jorge.data_api.model.PriceApiModel
import java.io.IOException

object TestModels {

    private val testPriceApiModel = PriceApiModel(1L, 4f)
    private val testPriceApiModel2 = PriceApiModel(16L, 14f)

    private val testPriceDataModel = PriceDataModel(1L, 4f)
    private val testPriceDataModel2 = PriceDataModel(16L, 14f)

    private val apiException = IOException("test api error")

    val givenAndExpectedPrices: List<Array<Any>> = listOf(
        arrayOf(
            ApiResult.Success(
                MarketPricesApiModel(
                    prices = listOf(
                        testPriceApiModel,
                        testPriceApiModel2
                    ), description = "test"
                )
            ),
            DataResult.Success(
                MarketPricesDataModel(
                    prices = listOf(
                        testPriceDataModel,
                        testPriceDataModel2
                    ), description = "test"
                )
            )
        ),
        arrayOf(
            ApiResult.Error(apiException),
            DataResult.Error(apiException)
        )
    )
}