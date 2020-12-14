package com.jorge.marketchart.ui.views

import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.jorge.marketchart.R
import com.jorge.marketchart.utils.TimeUtils

class CharMarkerView(context: Context) : MarkerView(context, R.layout.marker_view) {

    private val dateView: TextView = findViewById(R.id.date)
    private val valueView: TextView = findViewById(R.id.value)

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        e?.let {
            dateView.text = TimeUtils.longToTimeString(it.x.toLong())
            valueView.text = it.y.toString()
        }

        super.refreshContent(e, highlight)
    }
}