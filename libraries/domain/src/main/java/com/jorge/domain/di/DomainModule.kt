package com.jorge.domain.di

import com.jorge.domain.MarketPriceRepository
import com.jorge.domain.usecases.GetMarketPrices
import com.jorge.domain.usecases.GetMarketPricesImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DomainModule {

    @Provides
    @Singleton
    protected open fun provideGetPricesUseCase(repository: MarketPriceRepository): GetMarketPrices =
        GetMarketPricesImpl(repository)
}