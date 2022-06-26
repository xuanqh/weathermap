package com.weathermap.di

import com.weathermap.BuildConfig
import com.weathermap.data.remote.IServiceApi
import com.weathermap.data.remote.NetworkRequestHandler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single { createOkHttpClient() }
    single { provideAPIService<IServiceApi>(get()) }
}

fun createOkHttpClient(): OkHttpClient {
    val headersInterceptor = NetworkRequestHandler()

    val httpClient = OkHttpClient.Builder()
    httpClient.connectTimeout(1, TimeUnit.MINUTES)
    httpClient.readTimeout(1, TimeUnit.MINUTES)
    httpClient.writeTimeout(1, TimeUnit.MINUTES)

    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    httpClient.interceptors().add(logging)

    httpClient.addNetworkInterceptor(headersInterceptor)
    return httpClient.build()
}

inline fun <reified T> provideAPIService(okHttpClient: OkHttpClient): T {
    val url = BuildConfig.EVN_URL
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient).addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(T::class.java)
}