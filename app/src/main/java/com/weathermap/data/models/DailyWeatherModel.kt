package com.weathermap.data.models

import java.io.Serializable

data class DailyWeatherModel(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: TempModel,
    val feels_like: FeelsLikeModel,
    val pressure: Double,
    val humidity: Double,
    val weather: ArrayList<WeatherInfoModel>,
    val speed: Double,
    val deg: Int,
    val gust: Double,
    val clouds: Int,
    val pop: Double,
    val rain: Double,
) : Serializable