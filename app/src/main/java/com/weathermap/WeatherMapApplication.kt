package com.weathermap

import android.app.Application
import com.weathermap.di.repositoryModule
import com.weathermap.di.retrofitModule
import com.weathermap.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class WeatherMapApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        org.koin.core.context.startKoin {
            androidLogger(Level.NONE)
            androidContext(applicationContext)
            modules(
                retrofitModule,
                viewModelModule,
                repositoryModule
            )
        }
    }
}