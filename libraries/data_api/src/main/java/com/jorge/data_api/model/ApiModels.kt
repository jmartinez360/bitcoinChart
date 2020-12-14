package com.jorge.data_api.model

import com.google.gson.annotations.SerializedName

sealed class ApiResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception) : ApiResult<Nothing>()
}

data class PriceApiModel(
    @SerializedName("x")
    val timestamp: Long,
    @SerializedName("y")
    val value: Float
)

data class MarketPricesApiModel(
    @SerializedName("values")
    val prices: List<PriceApiModel>,
    val description: String
)