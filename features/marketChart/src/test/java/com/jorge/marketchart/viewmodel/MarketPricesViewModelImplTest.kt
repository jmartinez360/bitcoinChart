package com.jorge.marketchart.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jorge.domain.model.DomainResult
import com.jorge.domain.model.MarketPricesDomainModel
import com.jorge.domain.model.PriceDomainModel
import com.jorge.domain.usecases.GetMarketPrices
import com.jorge.marketchart.mapper.DomainMarketPriceToModelMapper
import com.jorge.marketchart.model.MarketPricesModel
import com.jorge.marketchart.model.PriceModel
import com.jorge.marketchart.model.Resource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.firstValue
import com.nhaarman.mockitokotlin2.lastValue
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@FlowPreview
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MarketPricesViewModelImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var getMarketPricesUseCase: GetMarketPrices

    @Mock
    private lateinit var mapper: DomainMarketPriceToModelMapper

    @Mock
    private lateinit var pricesObserver: Observer<Resource<MarketPricesModel>>

    @Captor
    private lateinit var resourceCaptor: ArgumentCaptor<Resource<MarketPricesModel>>

    private lateinit var viewModel: MarketPricesViewModel

    @Before
    fun setup() {
        viewModel = MarketPricesViewModelImpl(getMarketPricesUseCase, mapper)

        viewModel.marketPricesLiveData.observeForever(pricesObserver)
    }

    @Test
    fun `When getPrices Then should execute getPrices useCase`() {
        runBlockingTest {
            viewModel.getMarketPrices(TIME_SPAN)

            verify(getMarketPricesUseCase).execute(any())
        }
    }

    @Test
    fun `Given getPrices is invoked Then should show loading`() {
        viewModel.getMarketPrices(TIME_SPAN)

        verify(pricesObserver, times(2)).onChanged(resourceCaptor.capture())
        assertEquals(Resource.Loading, resourceCaptor.firstValue)
    }

    @Test
    fun `Given getPrices returns error Then should show error`() {
        runBlockingTest {
            setupErrorFlow()

            viewModel.getMarketPrices(TIME_SPAN)

            verify(pricesObserver, times(2)).onChanged(resourceCaptor.capture())
            assertEquals(Resource.Error(), resourceCaptor.lastValue)
        }
    }

    @Test
    fun `Given getPrices returns success Then should show right result`() {
        runBlockingTest {
            setupSuccessFlow()

            viewModel.getMarketPrices(TIME_SPAN)

            verify(pricesObserver, times(2)).onChanged(resourceCaptor.capture())
            assertEquals(Resource.Success(PRICE_MODEL), resourceCaptor.lastValue)
        }
    }

    private suspend fun setupErrorFlow() {
        val flow =
            flow<DomainResult<MarketPricesDomainModel>> { emit(DomainResult.Error(FLOW_EXCEPTION)) }
        whenever(getMarketPricesUseCase.execute(any())).thenReturn(flow)
    }

    private suspend fun setupSuccessFlow() {
        val flow =
            flow { emit(DOMAIN_RESULT) }
        whenever(getMarketPricesUseCase.execute(any())).thenReturn(flow)
        whenever(mapper.mapDomainPrices(any())).thenReturn(PRICE_MODEL)
    }

    companion object {
        private const val TIME_SPAN = "1week"
        private val FLOW_EXCEPTION = IOException("flow exception")

        private val DOMAIN_MODEL =
            MarketPricesDomainModel(prices = listOf(PriceDomainModel(1L, 4f)), description = "test")
        private val PRICE_MODEL = MarketPricesModel(prices = listOf(PriceModel(1L, 4f)), description = "test")

        private val DOMAIN_RESULT: DomainResult<MarketPricesDomainModel> =
            DomainResult.Success(DOMAIN_MODEL)
    }
}