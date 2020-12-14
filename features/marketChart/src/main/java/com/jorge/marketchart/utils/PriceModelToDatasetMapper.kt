package com.jorge.marketchart.utils

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.jorge.marketchart.model.MarketPricesModel
import com.jorge.marketchart.model.PriceModel

interface PriceModelToDatasetMapper {

    fun mapPricesToCharDataset(marketPrices: MarketPricesModel): LineDataSet
}

class PriceModelToDatasetMapperImpl : PriceModelToDatasetMapper {

    override fun mapPricesToCharDataset(marketPrices: MarketPricesModel): LineDataSet {
        return LineDataSet(marketPrices.prices.map { mapPrice(it) }, "")
    }


    private fun mapPrice(price: PriceModel): Entry {
        return Entry(
            price.timestamp.toFloat(),
            price.value
        )
    }
}