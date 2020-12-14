package com.jorge.data.repository

import com.jorge.data.PriceDataSource
import com.jorge.data.mapper.DataMarketPriceToDomainMarketPriceMapper
import com.jorge.data.model.DataResult
import com.jorge.data.model.MarketPricesDataModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MarketPriceRepositoryImplTest {

    @Mock
    lateinit var dataSource: PriceDataSource

    @Mock
    lateinit var mapper: DataMarketPriceToDomainMarketPriceMapper

    private lateinit var repository: MarketPriceRepositoryImpl

    @Before
    fun setup() {
        repository = MarketPriceRepositoryImpl(dataSource, mapper)
    }

    @Test
    fun `When  repositoryGetMarketPrices Then should call dataSource getPrices method`() {
        runBlockingTest {
            repository.getMarketPrices(TIME_SPAN)

            verify(dataSource).getMarketPrices(any())
        }
    }

    @Test
    fun `Given dataSource emits right result When repository getPrices Then call mapper`() {
        runBlockingTest {
            whenever(dataSource.getMarketPrices(any())).thenReturn(SUCCESS_FLOW)

            val flow = repository.getMarketPrices(TIME_SPAN)

            flow.collect { verify(mapper).mapDataResultToDomainResult(any()) }
        }
    }

    companion object {
        private const val TIME_SPAN = "1week"

        private val SUCCESS_FLOW = flow<DataResult<MarketPricesDataModel>> {
            emit(DataResult.Success(TestModels.TEST_MARKET_PRICES))
        }
    }
}