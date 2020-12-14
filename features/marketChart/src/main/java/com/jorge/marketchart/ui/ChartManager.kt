package com.jorge.marketchart.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.jorge.marketchart.R
import com.jorge.marketchart.model.MarketPricesModel
import com.jorge.marketchart.ui.views.CharMarkerView
import com.jorge.marketchart.utils.DateChartFormatterImpl
import com.jorge.marketchart.utils.PriceModelToDatasetMapper
import javax.inject.Inject

interface ChartManager {
    fun addChart(view: ViewGroup)
    fun showValues(marketPrices: MarketPricesModel)
    fun onLoading()
    fun onError()
}

@SuppressLint("InflateParams")
class ChartManagerImpl @Inject constructor(private val mapper: PriceModelToDatasetMapper) :
    ChartManager {

    private lateinit var chartView: LineChart

    override fun addChart(view: ViewGroup) {
        inflateChart(view)
        setupChartProperties(view)
        setupChartAxis()
        view.addView(chartView)
    }

    private fun inflateChart(view: ViewGroup) {
        val layoutInflater = LayoutInflater.from(view.context)
        chartView = layoutInflater.inflate(R.layout.chart_layout, null) as LineChart
    }

    private fun setupChartProperties(view: ViewGroup) {
        chartView.apply {
            description.isEnabled = false
            legend.isEnabled = false
            setPinchZoom(true)
            setDrawMarkers(true)
            marker = CharMarkerView(view.context)
            setBackgroundColor(chartView.context.getColor(R.color.background))
            setNoDataTextColor(chartView.context.getColor(R.color.labelColor))
            setNoDataTextTypeface(chartView.context.resources.getFont(R.font.roboto_medium))
        }
    }

    private fun setupChartAxis() {
        chartView.xAxis.apply {
            setLabelCount(5, true)
            setAvoidFirstLastClipping(true)
            disableGridDashedLine()
            valueFormatter = DateChartFormatterImpl()
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            axisLineColor = chartView.context.getColor(R.color.axisLine)
            axisLineWidth = chartView.context.resources.getInteger(R.integer.gridLine).toFloat()
            setCenterAxisLabels(true)
            textSize = chartView.context.resources.getInteger(R.integer.labelSize).toFloat()
            typeface = chartView.context.resources.getFont(R.font.roboto_regular)
            textColor = chartView.context.getColor(R.color.labelColor)
        }


        chartView.axisRight.apply {
            isEnabled = true
            setDrawAxisLine(false)
            gridColor = chartView.context.getColor(R.color.gridColor)
            gridLineWidth = chartView.context.resources.getInteger(R.integer.gridLine).toFloat()
            textSize = chartView.context.resources.getInteger(R.integer.labelSize).toFloat()
            typeface = chartView.context.resources.getFont(R.font.roboto_regular)
            textColor = chartView.context.getColor(R.color.labelColor)
        }

        chartView.axisLeft.isEnabled = false
    }

    override fun showValues(marketPrices: MarketPricesModel) {
        val valuesDataset = mapper.mapPricesToCharDataset(marketPrices)
        setupLineDataset(valuesDataset)

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(valuesDataset)
        chartView.data = LineData(dataSets)
        chartView.invalidate()
    }

    private fun setupLineDataset(valuesDataset: LineDataSet) {
        valuesDataset.apply {
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawCircles(false)
            color = chartView.context.getColor(R.color.charLine)
            lineWidth = chartView.context.resources.getInteger(R.integer.gridLine).toFloat()
            setDrawValues(false)
            highlightLineWidth = HiGHLIGHT_LINE_WIDTH
            highLightColor = chartView.context.getColor(R.color.axisLine)
            enableDashedHighlightLine(
                chartView.context.resources.getInteger(R.integer.dashed_line).toFloat(),
                chartView.context.resources.getInteger(R.integer.dashed_line_space).toFloat(),
                DASHED_PHASE
            )
        }
    }

    override fun onLoading() {
        chartView.setNoDataText(chartView.context.getString(R.string.loading_chart))
        chartView.clear()
        chartView.invalidate()
    }

    override fun onError() {
        chartView.setNoDataText(chartView.context.getString(R.string.error_chart))
        chartView.invalidate()
    }

    companion object {
        private const val HiGHLIGHT_LINE_WIDTH = 1.5f
        private const val DASHED_PHASE = 0f
    }
}