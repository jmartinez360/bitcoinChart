package com.jorge.marketchart.utils

import com.jorge.marketchart.di.DaggerMarketChartComponent
import com.jorge.marketchart.di.DomainTestingModule
import com.jorge.marketchart.di.MarketChartModule
import com.jorge.marketchart.ui.MarketChartFragment

class TestConfigurationBuilder {
    fun inject(fragment: MarketChartFragment) {
        DaggerMarketChartComponent.builder().marketChartModule(MarketChartModule(fragment))
            .domainModule(DomainTestingModule())
            .build()
            .inject(fragment)
    }
}

fun injectTestConfiguration(fragment: MarketChartFragment) {
    TestConfigurationBuilder().inject(fragment)
}


