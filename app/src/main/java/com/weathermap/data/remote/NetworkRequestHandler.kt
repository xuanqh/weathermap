package com.weathermap.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkRequestHandler : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return buildResponse(chain)
    }

    private fun buildResponse(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val request: Request = original.newBuilder()
            .method(original.method, original.body)
            .build()
        return chain.proceed(request)
    }
}