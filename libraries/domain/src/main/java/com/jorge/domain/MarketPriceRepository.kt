package com.jorge.domain

import com.jorge.domain.model.DomainResult
import com.jorge.domain.model.MarketPricesDomainModel
import kotlinx.coroutines.flow.Flow

interface MarketPriceRepository {

    suspend fun getMarketPrices(timeSpan: String): Flow<DomainResult<MarketPricesDomainModel>>
}