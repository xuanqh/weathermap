package com.weathermap.di

import com.weathermap.features.main.MainViewModel
import com.weathermap.features.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }

    viewModel {
        SettingViewModel()
    }
}