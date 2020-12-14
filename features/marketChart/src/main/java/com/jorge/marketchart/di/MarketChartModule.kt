package com.jorge.marketchart.di

import com.jorge.commons.extensions.viewModel
import com.jorge.domain.usecases.GetMarketPrices
import com.jorge.marketchart.mapper.DomainMarketPriceToModelMapper
import com.jorge.marketchart.mapper.DomainMarketPriceToModelMapperImpl
import com.jorge.marketchart.ui.ChartManager
import com.jorge.marketchart.ui.ChartManagerImpl
import com.jorge.marketchart.ui.MarketChartFragment
import com.jorge.marketchart.utils.FilterChipManager
import com.jorge.marketchart.utils.FilterChipManagerImpl
import com.jorge.marketchart.utils.PriceModelToDatasetMapper
import com.jorge.marketchart.utils.PriceModelToDatasetMapperImpl
import com.jorge.marketchart.viewmodel.MarketPricesViewModel
import com.jorge.marketchart.viewmodel.MarketPricesViewModelImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MarketChartModule(private val fragment: MarketChartFragment) {

    @Provides
    @Singleton
    fun provideDomainToModelMapper(): DomainMarketPriceToModelMapper =
        DomainMarketPriceToModelMapperImpl()

    @Provides
    fun provideMarketPricesViewModel(
        getMarketPricesUseCase: GetMarketPrices,
        mapper: DomainMarketPriceToModelMapper
    ): MarketPricesViewModel =
        fragment.viewModel { MarketPricesViewModelImpl(getMarketPricesUseCase, mapper) }

    @Provides
    @Singleton
    fun provideModelMapperToDataset(): PriceModelToDatasetMapper = PriceModelToDatasetMapperImpl()

    @Provides
    fun provideChartManager(mapper: PriceModelToDatasetMapper): ChartManager =
        ChartManagerImpl(mapper)

    @Provides
    fun provideChipManager(): FilterChipManager = FilterChipManagerImpl()
}