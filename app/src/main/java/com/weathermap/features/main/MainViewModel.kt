package com.weathermap.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.weathermap.domain.MainRepository
import com.weathermap.base.BaseViewModel
import com.weathermap.data.models.CityWeatherModel
import com.weathermap.nativelib.NativeLib
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository) : BaseViewModel() {
    private val _cityWeather = MutableLiveData<CityWeatherModel>()
    val cityWeather: LiveData<CityWeatherModel> = _cityWeather

    fun getCityWeather(city: String) {
        mainRepository.getCityWeather(
            query = city,
            count = 7,
            appid = NativeLib.getWeatherMapKey(),
            units = "metric"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isSuccessful && it.body() != null) {
                        _cityWeather.value = it.body()
                    }else if(it.errorBody()!=null){
                        _cityWeather.value = null
                        it.errorBody()?.let {errorBody->
                            parseErrorResponse(errorBody)
                        }
                    }else{
                        _cityWeather.value = null
                        handleErrorMessage.value ="Error unknown!"
                    }
                },
                {
                    _cityWeather.value = null
                    parseErrorException(it)
                }
            ).addTo(compositeDisposables)
    }
}