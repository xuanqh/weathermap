package com.weathermap.data.models

import java.io.Serializable

data class TempModel(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double,
) : Serializable