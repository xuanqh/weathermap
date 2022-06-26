package com.weathermap.data.models

import java.io.Serializable

data class ErrorResponse(val cod: Int, val message: String) : Serializable