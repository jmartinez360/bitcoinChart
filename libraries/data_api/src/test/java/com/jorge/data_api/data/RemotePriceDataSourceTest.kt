package com.jorge.data_api.data

import com.jorge.data.PriceDataSource
import com.jorge.data_api.MarketPricesApiService
import com.jorge.data_api.mapper.ApiPriceToDataSourcePriceMapper
import com.jorge.data_api.model.MarketPricesApiModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemotePriceDataSourceTest {

    @Mock
    lateinit var apiService: MarketPricesApiService

    @Mock
    lateinit var mapper: ApiPriceToDataSourcePriceMapper

    private lateinit var dataSource: PriceDataSource

    @Before
    fun setUp() {
        dataSource = RemotePriceDataSource(apiService, mapper)
    }

    @Test
    fun `when getMarketPrices then apiService is invoked`() {
        runBlockingTest {
            whenever(apiService.getBitcoinMarketPrices(any())).thenReturn(getMarketPriceResponse())

            dataSource.getMarketPrices("1week")

            verify(apiService, times(1)).getBitcoinMarketPrices(any())
        }
    }

    private fun getMarketPriceResponse() =
        Response.success(MarketPricesApiModel(emptyList(), ""))
}