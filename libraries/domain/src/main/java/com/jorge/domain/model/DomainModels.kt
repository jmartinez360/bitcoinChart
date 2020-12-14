package com.jorge.domain.model

sealed class DomainResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : DomainResult<T>()
    data class Error(val exception: Exception) : DomainResult<Nothing>()
}

data class PriceDomainModel(
    val timestamp: Long,
    val value: Float
)

data class MarketPricesDomainModel(
    val prices: List<PriceDomainModel>,
    val description: String
)