package com.jorge.marketchart

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jorge.marketchart.utils.MarketCharTestingFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MarketCharFragmentTest {

    @Before
    fun setup() {
        launchFragmentInContainer<MarketCharTestingFragment>(null, R.style.Theme_MaterialComponents)
    }

    @Test
    fun whenReceiveData_ShouldShowRightTitle() {
        marketChartRobot {
            assertChartTitleIsRight("Testing data")
            assertChipIsChecked(MarketChartRobot.oneWeekChipMatcher)
        }
    }

    @Test
    fun whenPressNextFilter_ShouldSelectNewChip() {
        marketChartRobot {
            assertChipIsChecked(MarketChartRobot.oneWeekChipMatcher)
            checkChip(MarketChartRobot.thirtyDaysChipMatcher)
            assertChipIsChecked(MarketChartRobot.thirtyDaysChipMatcher)
            assertChipIsNotChecked(MarketChartRobot.oneWeekChipMatcher)
        }
    }
}