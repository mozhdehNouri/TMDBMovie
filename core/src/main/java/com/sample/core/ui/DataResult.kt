package com.sample.core.ui

/**
 * A generic wrapper class around data request
 */
data class DataResult<RequestData>(
    var responseType: Status,
    var data: RequestData? = null,
    var error: Exception? = null
)

enum class Status { SUCCESSFUL, ERROR, LOADING }
