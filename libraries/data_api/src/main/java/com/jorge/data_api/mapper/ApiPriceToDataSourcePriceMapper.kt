package com.jorge.data_api.mapper

import com.jorge.data.model.DataResult
import com.jorge.data.model.MarketPricesDataModel
import com.jorge.data.model.PriceDataModel
import com.jorge.data_api.model.ApiResult
import com.jorge.data_api.model.MarketPricesApiModel
import com.jorge.data_api.model.PriceApiModel

interface ApiPriceToDataSourcePriceMapper {

    fun mapApiPricesResultToDataPricesResult(result: ApiResult<MarketPricesApiModel>): DataResult<MarketPricesDataModel>
}

class ApiPriceToDataPriceMapperImpl : ApiPriceToDataSourcePriceMapper {
    override fun mapApiPricesResultToDataPricesResult(result: ApiResult<MarketPricesApiModel>): DataResult<MarketPricesDataModel> {
        return when (result) {
            is ApiResult.Success -> DataResult.Success(mapMarketPrices(result.data))
            is ApiResult.Error -> DataResult.Error(result.exception)
        }
    }

    private fun mapMarketPrices(marketPrices: MarketPricesApiModel) = MarketPricesDataModel(
        prices = marketPrices.prices.map { mapPrice(it) },
        description = marketPrices.description
    )

    private fun mapPrice(price: PriceApiModel) = PriceDataModel(
        timestamp = price.timestamp,
        value = price.value
    )
}