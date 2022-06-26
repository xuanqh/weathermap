package com.weathermap.domain

import com.weathermap.data.models.CityWeatherModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface MainRepository {
    fun getCityWeather(
        query: String,
        count: Int,
        appid: String,
        units: String
    ): Single<Response<CityWeatherModel>>
}