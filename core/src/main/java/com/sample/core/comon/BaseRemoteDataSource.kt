package com.sample.core.comon


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

abstract class BaseRemoteDataSource(
    context: Context,
) {

    private val connectivityManager: ConnectivityManager =
        context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): BaseResult<T> {
        return withContext(
            Dispatchers.IO
        ) {
            if (isNetworkAvailable()) {
                try {
                    val response =
                        apiCall.invoke()
                    if (response.isSuccessful) {
                        val body =
                            response.body()
                        if (body != null) {
                            BaseResult.Success(
                                body
                            )
                        } else {
                            BaseResult.Error(
                                IOException(
                                    "Empty response body"
                                )
                            )
                        }
                    } else {
                        BaseResult.Error(
                            IOException(
                                "Unsuccessful response: ${response.code()}"
                            )
                        )
                    }
                } catch (e: Exception) {
                    BaseResult.Error(
                        e
                    )
                }
            } else {
                BaseResult.Error(
                    IOException(
                        "No internet connection"
                    )
                )
            }
        }
    }


    private fun isNetworkAvailable(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities =
                connectivityManager.activeNetwork
                    ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(
                    networkCapabilities
                )
                    ?: return false
            activeNetwork.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            )
        } else {
            @Suppress(
                "DEPRECATION"
            )
            val networkInfo =
                connectivityManager.activeNetworkInfo
                    ?: return false
            @Suppress(
                "DEPRECATION"
            )
            networkInfo.isConnected
        }
    }
}


