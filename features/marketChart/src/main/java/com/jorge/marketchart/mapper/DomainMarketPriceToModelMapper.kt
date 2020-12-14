package com.jorge.marketchart.mapper

import com.jorge.domain.model.MarketPricesDomainModel
import com.jorge.domain.model.PriceDomainModel
import com.jorge.marketchart.model.MarketPricesModel
import com.jorge.marketchart.model.PriceModel

interface DomainMarketPriceToModelMapper {

    fun mapDomainPrices(prices: MarketPricesDomainModel): MarketPricesModel
}

class DomainMarketPriceToModelMapperImpl : DomainMarketPriceToModelMapper {

    override fun mapDomainPrices(prices: MarketPricesDomainModel) = MarketPricesModel(
        prices = prices.prices.map { mapPrice(it) },
        description = prices.description
    )

    private fun mapPrice(price: PriceDomainModel) = PriceModel(
        timestamp = price.timestamp,
        value = price.value
    )
}