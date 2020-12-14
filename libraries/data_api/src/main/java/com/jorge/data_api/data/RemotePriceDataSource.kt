package com.jorge.data_api.data

import com.jorge.data.PriceDataSource
import com.jorge.data.model.DataResult
import com.jorge.data.model.MarketPricesDataModel
import com.jorge.data_api.MarketPricesApiService
import com.jorge.data_api.mapper.ApiPriceToDataSourcePriceMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemotePriceDataSource @Inject constructor(
    private val apiService: MarketPricesApiService,
    private val mapper: ApiPriceToDataSourcePriceMapper
) : RemoteDataSource(), PriceDataSource {

    override suspend fun getMarketPrices(timeSpan: String): Flow<DataResult<MarketPricesDataModel>> {
        return try {
            val response = apiService.getBitcoinMarketPrices(timeSpan)
            val apiResult = handleApiResponse(response)
            val result = mapper.mapApiPricesResultToDataPricesResult(apiResult)
            flow { emit(result) }
        } catch (error: Exception) {
            flow { emit(DataResult.Error(error)) }
        }
    }
}