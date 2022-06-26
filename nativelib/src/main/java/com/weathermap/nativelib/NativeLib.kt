package com.weathermap.nativelib

object NativeLib {

    /**
     * A native method that is implemented by the 'nativelib' native library,
     * which is packaged with this application.
     */
    private external fun weatherMapKey(): String

    init {
        System.loadLibrary("nativelib")
    }

    fun getWeatherMapKey(): String {
        return weatherMapKey()
    }
}