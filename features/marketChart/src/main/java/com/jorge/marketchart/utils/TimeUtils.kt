package com.jorge.marketchart.utils

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
object TimeUtils {


    fun longToTimeString(time: Long): String {
        val sdf = SimpleDateFormat("dd-MMM")
        return sdf.format(Date(time * 1000))
    }
}