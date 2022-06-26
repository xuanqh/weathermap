package com.weathermap.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.weathermap.data.models.ErrorResponse
import io.reactivex.rxjava3.disposables.CompositeDisposable
import okhttp3.ResponseBody
import retrofit2.HttpException

open class BaseViewModel : ViewModel() {
    protected val compositeDisposables = CompositeDisposable()
    val handleErrorMessage: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        compositeDisposables.dispose()
    }

    fun parseErrorException(throwable: Throwable) {
        if (throwable is HttpException) {
            handleErrorMessage.value = throwable.message()
        }else{
            handleErrorMessage.value = throwable.message
        }
    }

    fun parseErrorResponse(errorBody: ResponseBody) {
        val errorResponse: ErrorResponse = Gson().fromJson(errorBody.string(), ErrorResponse::class.java)
        handleErrorMessage.value = "${errorResponse.cod}: ${errorResponse.message}"
    }
}
