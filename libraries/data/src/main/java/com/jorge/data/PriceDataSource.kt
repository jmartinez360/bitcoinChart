package com.jorge.data

import com.jorge.data.model.DataResult
import com.jorge.data.model.MarketPricesDataModel
import kotlinx.coroutines.flow.Flow

interface PriceDataSource {

    suspend fun getMarketPrices(timeSpan: String): Flow<DataResult<MarketPricesDataModel>>
}