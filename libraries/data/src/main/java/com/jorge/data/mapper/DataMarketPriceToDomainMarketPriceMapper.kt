package com.jorge.data.mapper

import com.jorge.data.model.DataResult
import com.jorge.data.model.MarketPricesDataModel
import com.jorge.data.model.PriceDataModel
import com.jorge.domain.model.DomainResult
import com.jorge.domain.model.MarketPricesDomainModel
import com.jorge.domain.model.PriceDomainModel

interface DataMarketPriceToDomainMarketPriceMapper {

    fun mapDataResultToDomainResult(result: DataResult<MarketPricesDataModel>): DomainResult<MarketPricesDomainModel>
}

class DataMarketPriceToDomainMarketPriceMapperImpl : DataMarketPriceToDomainMarketPriceMapper {

    override fun mapDataResultToDomainResult(result: DataResult<MarketPricesDataModel>): DomainResult<MarketPricesDomainModel> {
        return when (result) {
            is DataResult.Success -> DomainResult.Success(mapMarketPrices(result.data))
            is DataResult.Error -> DomainResult.Error(result.exception)
        }
    }

    private fun mapMarketPrices(marketPrices: MarketPricesDataModel) = MarketPricesDomainModel(
        prices = marketPrices.prices.map { mapPrice(it) },
        description = marketPrices.description
    )

    private fun mapPrice(price: PriceDataModel) = PriceDomainModel(
        timestamp = price.timestamp,
        value = price.value
    )
}