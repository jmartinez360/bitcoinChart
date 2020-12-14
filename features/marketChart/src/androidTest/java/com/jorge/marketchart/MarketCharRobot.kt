package com.jorge.marketchart

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher

fun marketChartRobot(func: MarketChartRobot.() -> Unit) = MarketChartRobot().apply { func() }

class MarketChartRobot {

    fun assertChartTitleIsRight(expectedTitle: String) = apply {
        Espresso.onView(titleMatcher)
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedTitle)))
    }

    fun assertChipIsChecked(chipMatcher: Matcher<View>) {
        Espresso.onView(chipMatcher)
            .check(ViewAssertions.matches(ViewMatchers.isChecked()))
    }

    fun assertChipIsNotChecked(chipMatcher: Matcher<View>) {
        Espresso.onView(chipMatcher)
            .check(ViewAssertions.matches(ViewMatchers.isNotChecked()))
    }

    fun checkChip(chipMatcher: Matcher<View>) {
        Espresso.onView(chipMatcher).perform(click())
    }

    companion object {
        private val titleMatcher = withId(R.id.chartTitle)
        val oneWeekChipMatcher = withId(R.id.one_week)
        val thirtyDaysChipMatcher = withId(R.id.thirty_days)
    }
}