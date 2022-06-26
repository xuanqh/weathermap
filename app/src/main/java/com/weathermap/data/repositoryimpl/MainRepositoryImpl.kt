package com.weathermap.data.repositoryimpl

import com.weathermap.data.models.CityWeatherModel
import com.weathermap.data.remote.IServiceApi
import com.weathermap.domain.MainRepository
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class MainRepositoryImpl(private val serviceApi: IServiceApi) : MainRepository {
    override fun getCityWeather(
        query: String,
        count: Int,
        appid: String,
        units: String
    ): Single<Response<CityWeatherModel>> {
        return serviceApi.getCityWeather(query, count, appid, units)
    }
}