package com.jorge.marketchart.di

import com.jorge.data.di.DataModule
import com.jorge.data_api.di.ApiModule
import com.jorge.domain.di.DomainModule
import com.jorge.marketchart.ui.MarketChartFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MarketChartModule::class,
        DomainModule::class,
        DataModule::class,
        ApiModule::class
    ]
)
interface MarketChartComponent {
    fun inject(fragment: MarketChartFragment)

}