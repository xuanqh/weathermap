package com.weathermap.data.models

import java.io.Serializable

data class WeatherInfoModel(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
) : Serializable