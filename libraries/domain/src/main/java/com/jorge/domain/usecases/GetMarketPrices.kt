package com.jorge.domain.usecases

import com.jorge.domain.MarketPriceRepository
import com.jorge.domain.model.DomainResult
import com.jorge.domain.model.MarketPricesDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetMarketPrices {
    suspend fun execute(timeSpan: String): Flow<DomainResult<MarketPricesDomainModel>>
}

class GetMarketPricesImpl @Inject constructor(private val repository: MarketPriceRepository) :
    GetMarketPrices {

    override suspend fun execute(timeSpan: String) =
        withContext(Dispatchers.IO) { repository.getMarketPrices(timeSpan) }
}