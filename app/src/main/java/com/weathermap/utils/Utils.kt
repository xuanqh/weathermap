package com.weathermap.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun getDate(timestamp: Long) :String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp * 1000L
        val sdf = SimpleDateFormat("dd MMM yyyy")
        val date = sdf.format(calendar.time)
        return date
    }
}