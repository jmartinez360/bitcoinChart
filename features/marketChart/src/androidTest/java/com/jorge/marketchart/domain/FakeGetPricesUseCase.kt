package com.jorge.marketchart.domain

import com.jorge.domain.model.DomainResult
import com.jorge.domain.model.MarketPricesDomainModel
import com.jorge.domain.usecases.GetMarketPrices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetPricesUseCase : GetMarketPrices {
    override suspend fun execute(timeSpan: String): Flow<DomainResult<MarketPricesDomainModel>> {
        return flow { emit(DomainResult.Success(TestModels.TEST_MARKET_PRICES)) }
    }
}