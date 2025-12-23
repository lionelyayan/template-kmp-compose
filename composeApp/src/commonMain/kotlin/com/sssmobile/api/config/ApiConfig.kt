package com.sssmobile.api.config

import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import kotlinx.coroutines.delay
import kotlinx.io.IOException

object ApiConfig {
    var ENV: ApiEnvironment = ApiEnvironment.DEV

    val BASE_URL: String
        get() = ENV.baseUrl
}

suspend fun <T> retryRequest(
    times: Int = RETRY_COUNT,
    delayMillis: Long = DELAY,
    block: suspend () -> T
): T {
    repeat(times - 1) {
        try {
            return block()
        } catch (e: Exception) {
            if (!isRetryableError(e)) throw e
            delay(delayMillis)
        }
    }
    return block()
}

fun isRetryableError(e: Throwable): Boolean {
    return when (e) {
        is ConnectTimeoutException -> true
        is SocketTimeoutException -> true
        is IOException -> true   // termasuk lost connection, broken pipe, dll.
        else -> false
    }
}