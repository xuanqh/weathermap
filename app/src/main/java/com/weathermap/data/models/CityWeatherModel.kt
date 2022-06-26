package com.weathermap.data.models

import java.io.Serializable

data class CityWeatherModel(
    val city: CityModel,
    val code: Int,
    val message: String,
    val cnt: Int,
    val list: ArrayList<DailyWeatherModel>
) : Serializable