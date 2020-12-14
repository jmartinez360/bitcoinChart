package com.jorge.data_api.mapper

import com.jorge.data.model.DataResult
import com.jorge.data.model.MarketPricesDataModel
import com.jorge.data_api.model.ApiResult
import com.jorge.data_api.model.MarketPricesApiModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ApiPriceToDataSourcePriceMapperImplTest(
    private val givenApiPrices: ApiResult<MarketPricesApiModel>,
    private val expectedDataPrices: DataResult<MarketPricesDataModel>
) {

    private lateinit var mapper: ApiPriceToDataSourcePriceMapper

    @Before
    fun setUp() {
        mapper = ApiPriceToDataPriceMapperImpl()
    }

    @Test
    fun `Given apiMarketPrices when toDataPrices then returns expected result`() {
        val currentValue = mapper.mapApiPricesResultToDataPricesResult(givenApiPrices)

        assertEquals(expectedDataPrices, currentValue)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun getParams() = TestModels.givenAndExpectedPrices
    }
}