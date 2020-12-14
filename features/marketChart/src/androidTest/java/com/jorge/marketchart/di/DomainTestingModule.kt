package com.jorge.marketchart.di

import com.jorge.domain.MarketPriceRepository
import com.jorge.domain.di.DomainModule
import com.jorge.domain.usecases.GetMarketPrices
import com.jorge.marketchart.domain.FakeGetPricesUseCase

class DomainTestingModule: DomainModule() {

    override fun provideGetPricesUseCase(repository: MarketPriceRepository): GetMarketPrices {
        return FakeGetPricesUseCase()
    }
}