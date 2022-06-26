package com.weathermap.di

import com.weathermap.domain.MainRepository
import com.weathermap.data.repositoryimpl.MainRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<MainRepository> { MainRepositoryImpl(get()) }
}