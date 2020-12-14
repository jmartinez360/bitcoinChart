package com.jorge.marketchart.model

import com.jorge.marketchart.R

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val exception: Exception? = null) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}

data class PriceModel(
    val timestamp: Long,
    val value: Float
)

data class MarketPricesModel(
    val prices: List<PriceModel>,
    val description: String
)

enum class PriceFilterType(val filterValue: String, val filterRes: Int, val id: Int) {
    ONE_WEEK("1week", R.string.one_week, R.id.one_week),
    THIRTY_DAYS("30days", R.string.thirty_days, R.id.thirty_days),
    SIXTY_DAYS("60days", R.string.sixty_days, R.id.sixty_days),
    ONE_YEAR("1year", R.string.one_year, R.id.one_year),
    CRASH("crash", R.string.crash, R.id.crash)
}