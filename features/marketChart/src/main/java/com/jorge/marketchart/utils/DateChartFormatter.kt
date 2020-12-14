package com.jorge.marketchart.utils

import com.github.mikephil.charting.formatter.ValueFormatter

abstract class DateChartFormatter : ValueFormatter()

class DateChartFormatterImpl : DateChartFormatter() {

    override fun getFormattedValue(value: Float): String {
        return TimeUtils.longToTimeString(value.toLong())
    }
}