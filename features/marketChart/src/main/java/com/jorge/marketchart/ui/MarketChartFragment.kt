package com.jorge.marketchart.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jorge.marketchart.R
import com.jorge.marketchart.databinding.FragmentMarketChartBinding
import com.jorge.marketchart.di.DaggerMarketChartComponent
import com.jorge.marketchart.di.MarketChartModule
import com.jorge.marketchart.model.MarketPricesModel
import com.jorge.marketchart.model.Resource
import com.jorge.marketchart.utils.FilterChipManager
import com.jorge.marketchart.viewmodel.MarketPricesViewModel
import javax.inject.Inject

open class MarketChartFragment : Fragment(R.layout.fragment_market_chart) {

    @Inject
    lateinit var viewModel: MarketPricesViewModel

    @Inject
    lateinit var chartProvider: ChartManager

    @Inject
    lateinit var chipsManager: FilterChipManager

    private lateinit var binding: FragmentMarketChartBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMarketChartBinding.bind(view)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        setupChart()
        setupFilterChips()
    }

    private fun setupChart() {
        chartProvider.addChart(binding.chartContainer)
    }

    private fun setupFilterChips() {
        chipsManager.addChipSelectedAction { viewModel.getMarketPrices(it) }
        chipsManager.addFilterChips(binding.chipGroup)
    }

    private fun setupObservers() {
        viewModel.marketPricesLiveData.observe(viewLifecycleOwner, Observer {
            onPricesResultReceived(it)
        })
    }

    private fun onPricesResultReceived(pricesResource: Resource<MarketPricesModel>?) {
        when (pricesResource) {
            is Resource.Success -> setupValues(pricesResource.data)
            is Resource.Loading -> chartProvider.onLoading()
            is Resource.Error -> chartProvider.onError()
        }
    }

    private fun setupValues(prices: MarketPricesModel) {
        chartProvider.showValues(prices)
        binding.chartTitle.text = prices.description
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectMembers()
    }

    protected open fun injectMembers() =
        DaggerMarketChartComponent.builder().marketChartModule(MarketChartModule(this)).build()
            .inject(this)
}