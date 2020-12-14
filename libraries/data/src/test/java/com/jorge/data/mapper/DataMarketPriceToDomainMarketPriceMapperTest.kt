package com.jorge.data.mapper

import com.jorge.data.model.DataResult
import com.jorge.data.model.MarketPricesDataModel
import com.jorge.domain.model.DomainResult
import com.jorge.domain.model.MarketPricesDomainModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class DataMarketPriceToDomainMarketPriceMapperTest(
    private val givenDataPrices: DataResult<MarketPricesDataModel>,
    private val expectedDomainPrices: DomainResult<MarketPricesDomainModel>
) {

    private lateinit var mapper: DataMarketPriceToDomainMarketPriceMapper

    @Before
    fun setUp() {
        mapper = DataMarketPriceToDomainMarketPriceMapperImpl()
    }

    @Test
    fun `Given dataMarketPrices when toDomainPrices then returns expected result`() {
        val currentValue = mapper.mapDataResultToDomainResult(givenDataPrices)

        assertEquals(expectedDomainPrices, currentValue)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun getParams() = TestModels.givenAndExpectedPrices
    }
}