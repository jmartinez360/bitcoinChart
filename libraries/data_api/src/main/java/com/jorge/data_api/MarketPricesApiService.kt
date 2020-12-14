package com.jorge.data_api

import com.jorge.data_api.model.MarketPricesApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarketPricesApiService {
    @GET("/charts/market-price")
    suspend fun getBitcoinMarketPrices(@Query("timespan") timeSpan: String): Response<MarketPricesApiModel>
}

