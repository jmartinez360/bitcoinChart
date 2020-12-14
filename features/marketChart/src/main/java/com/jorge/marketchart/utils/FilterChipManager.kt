package com.jorge.marketchart.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.jorge.marketchart.R
import com.jorge.marketchart.model.PriceFilterType

interface FilterChipManager {

    fun addFilterChips(chipGroup: ChipGroup)

    fun addChipSelectedAction(action: (filterValue: String) -> Unit)
}

@SuppressLint("InflateParams")
class FilterChipManagerImpl : FilterChipManager {

    private var chipsMap: MutableMap<Int, PriceFilterType> = mutableMapOf()

    private var filterSelectedAction: ((filterValue: String) -> Unit)? = null

    override fun addFilterChips(chipGroup: ChipGroup) {
        createFilterChips(chipGroup)
        addChipGroupSelectionListener(chipGroup)
        selectDefaultFilter(chipGroup)
    }

    private fun createFilterChips(chipGroup: ChipGroup) {
        val layoutInflater = LayoutInflater.from(chipGroup.context)
        PriceFilterType.values().forEach { addChipToGroup(layoutInflater, chipGroup, it) }
    }

    private fun addChipToGroup(
        layoutInflater: LayoutInflater,
        chipGroup: ChipGroup,
        filterType: PriceFilterType
    ) {
        val chip = layoutInflater.inflate(R.layout.chart_filter_chip, null) as Chip
        chip.id = filterType.id
        chip.text = layoutInflater.context.getString(filterType.filterRes)
        chipGroup.addView(chip)
        chipsMap[chip.id] = filterType
    }

    private fun addChipGroupSelectionListener(group: ChipGroup) {
        group.setOnCheckedChangeListener { _, checkedId ->
            invokeFilterSelectedAction(checkedId)
        }
    }

    private fun selectDefaultFilter(group: ChipGroup) {
        group.check(chipsMap.keys.first())
        invokeFilterSelectedAction(chipsMap.keys.first())
    }

    private fun invokeFilterSelectedAction(checkedId: Int) {
        filterSelectedAction?.invoke(chipsMap.getValue(checkedId).filterValue)
    }

    override fun addChipSelectedAction(action: (filterValue: String) -> Unit) {
        filterSelectedAction = action
    }
}