package com.jorge.data.model

sealed class DataResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : DataResult<T>()
    data class Error(val exception: Exception) : DataResult<Nothing>()
}

data class PriceDataModel(
    val timestamp: Long,
    val value: Float
)

data class MarketPricesDataModel(
    val prices: List<PriceDataModel>,
    val description: String
)