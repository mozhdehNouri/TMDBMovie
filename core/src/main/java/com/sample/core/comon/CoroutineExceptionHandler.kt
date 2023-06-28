package com.sample.core.comon

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun CoroutineScope.launchWithErrorHandler(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> Unit
) = run {
    val errorHandler = CoroutineExceptionHandler { _, error ->
        when (error) {
            is IllegalStateException -> {
                Log.d(
                    "AutoClearedValueTAG",
                    "Tried to access value after fragment view is destroyed"
                )
            }

            else -> throw error
        }
    }

    launch(
        context = context + errorHandler,
        block = block
    )
}