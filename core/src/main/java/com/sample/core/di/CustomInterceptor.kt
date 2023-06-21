package com.sample.core.di

import com.sample.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        requestBuilder.addHeader("Authorization", BuildConfig.TOKEN)
        requestBuilder.addHeader("accept", "application/json")
        val response = chain.proceed(requestBuilder.build())

        if (response.code == 401) {

            // UnauthorizedError
        }
        return response
    }
}