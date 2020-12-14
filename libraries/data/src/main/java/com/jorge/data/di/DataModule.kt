package com.jorge.data.di

import com.jorge.data.PriceDataSource
import com.jorge.data.mapper.DataMarketPriceToDomainMarketPriceMapper
import com.jorge.data.mapper.DataMarketPriceToDomainMarketPriceMapperImpl
import com.jorge.data.repository.MarketPriceRepositoryImpl
import com.jorge.domain.MarketPriceRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideDataToDomainMapper(): DataMarketPriceToDomainMarketPriceMapper =
        DataMarketPriceToDomainMarketPriceMapperImpl()

    @Singleton
    @Provides
    fun provideMarketPricesRepository(
        dataSource: PriceDataSource,
        mapper: DataMarketPriceToDomainMarketPriceMapper
    ): MarketPriceRepository =
        MarketPriceRepositoryImpl(dataSource, mapper)
}