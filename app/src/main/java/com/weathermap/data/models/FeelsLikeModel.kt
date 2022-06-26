package com.weathermap.data.models

import java.io.Serializable

data class FeelsLikeModel(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
) : Serializable