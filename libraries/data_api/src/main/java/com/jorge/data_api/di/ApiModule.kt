package com.jorge.data_api.di

import com.google.gson.GsonBuilder
import com.jorge.data.PriceDataSource
import com.jorge.data_api.MarketPricesApiService
import com.jorge.data_api.data.RemotePriceDataSource
import com.jorge.data_api.mapper.ApiPriceToDataPriceMapperImpl
import com.jorge.data_api.mapper.ApiPriceToDataSourcePriceMapper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
        .baseUrl("https://api.blockchain.info/")
        .build()

    @Singleton
    @Provides
    fun provideMarketPricesService(retrofit: Retrofit): MarketPricesApiService =
        retrofit.create(MarketPricesApiService::class.java)

    @Singleton
    @Provides
    fun provideApiPriceToDataPriceMapper(): ApiPriceToDataSourcePriceMapper =
        ApiPriceToDataPriceMapperImpl()

    @Singleton
    @Provides
    fun provideRemotePriceDataSource(
        apiService: MarketPricesApiService,
        mapper: ApiPriceToDataSourcePriceMapper
    ): PriceDataSource =
        RemotePriceDataSource(apiService, mapper)
}