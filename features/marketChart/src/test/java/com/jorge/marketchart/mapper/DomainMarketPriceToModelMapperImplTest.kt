package com.jorge.marketchart.mapper

import com.jorge.domain.model.MarketPricesDomainModel
import com.jorge.marketchart.model.MarketPricesModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class DomainMarketPriceToModelMapperImplTest(
    private val givenDomainPrices: MarketPricesDomainModel,
    private val expectedModelPrices: MarketPricesModel
) {
    private lateinit var mapper: DomainMarketPriceToModelMapperImpl

    @Before
    fun setUp() {
        mapper = DomainMarketPriceToModelMapperImpl()
    }

    @Test
    fun `Given domainMarketPrices when toModelPrices then returns expected result`() {
        val currentValue = mapper.mapDomainPrices(givenDomainPrices)

        assertEquals(expectedModelPrices, currentValue)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun getParams() = TestObjects.givenAndExpectedPrices
    }
}