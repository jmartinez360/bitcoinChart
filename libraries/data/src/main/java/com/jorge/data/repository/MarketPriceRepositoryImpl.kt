package com.jorge.data.repository

import com.jorge.data.PriceDataSource
import com.jorge.data.mapper.DataMarketPriceToDomainMarketPriceMapper
import com.jorge.domain.MarketPriceRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MarketPriceRepositoryImpl @Inject constructor(
    private val dataSource: PriceDataSource,
    private val mapper: DataMarketPriceToDomainMarketPriceMapper
) : MarketPriceRepository {

    override suspend fun getMarketPrices(timeSpan: String) =
        dataSource.getMarketPrices(timeSpan).map { mapper.mapDataResultToDomainResult(it) }
}