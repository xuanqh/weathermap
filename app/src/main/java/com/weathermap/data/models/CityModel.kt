package com.weathermap.data.models

import java.io.Serializable

data class CityModel(
    val id: Int,
    val name: String,
    val coord: CoordModel,
    val country: String,
    val population: Long,
    val timezone: Int
) : Serializable