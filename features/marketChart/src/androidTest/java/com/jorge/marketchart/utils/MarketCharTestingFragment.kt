package com.jorge.marketchart.utils

import com.jorge.marketchart.ui.MarketChartFragment

class MarketCharTestingFragment: MarketChartFragment() {

    override fun injectMembers() {
        injectTestConfiguration(this)
    }
}