package com.weathermap.data.remote

import com.weathermap.data.models.CityWeatherModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IServiceApi {
    @GET("forecast/daily")
    fun getCityWeather(
        @Query("q") query: String,
        @Query("cnt") count: Int,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): Single<Response<CityWeatherModel>>
}