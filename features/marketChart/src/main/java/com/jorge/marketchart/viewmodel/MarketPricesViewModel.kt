package com.jorge.marketchart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorge.domain.model.DomainResult
import com.jorge.domain.model.MarketPricesDomainModel

import com.jorge.domain.usecases.GetMarketPrices
import com.jorge.marketchart.mapper.DomainMarketPriceToModelMapper
import com.jorge.marketchart.model.MarketPricesModel
import com.jorge.marketchart.model.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class MarketPricesViewModel : ViewModel() {

    abstract fun getMarketPrices(timeSpan: String)

    abstract val marketPricesLiveData: LiveData<Resource<MarketPricesModel>>
}

class MarketPricesViewModelImpl @Inject constructor(
    private val getMarketPrices: GetMarketPrices,
    private val mapper: DomainMarketPriceToModelMapper
) :
    MarketPricesViewModel() {

    private val _marketPricesLiveData = MediatorLiveData<Resource<MarketPricesModel>>()
    override val marketPricesLiveData: LiveData<Resource<MarketPricesModel>>
        get() = _marketPricesLiveData

    override fun getMarketPrices(timeSpan: String) {
        viewModelScope.launch { getPrices(timeSpan) }
    }

    private suspend fun getPrices(timeSpan: String) {
        showLoading()
        getMarketPrices.execute(timeSpan)
            .catch { onError() }
            .collect { onResult(it) }
    }

    private fun showLoading() {
        _marketPricesLiveData.postValue(Resource.Loading)
    }

    private fun onResult(result: DomainResult<MarketPricesDomainModel>) {
        when (result) {
            is DomainResult.Success -> mapAndPostPrices(result.data)
            is DomainResult.Error -> onError()
        }
    }

    private fun mapAndPostPrices(prices: MarketPricesDomainModel) {
        val currentPrices = mapper.mapDomainPrices(prices)
        _marketPricesLiveData.postValue(Resource.Success(currentPrices))
    }

    private fun onError() {
        _marketPricesLiveData.postValue(Resource.Error())
    }
}