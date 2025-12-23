package com.sssmobile.api

import com.sssmobile.api.config.ApiError
import com.sssmobile.api.config.ApiException
import com.sssmobile.api.config.TIMEOUT
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object ApiClient {

    val client = HttpClient {

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = true
                }
            )
        }

        install(Logging) {
            level = LogLevel.ALL
        }

        // pakai TIMEOUT dari ApiEnvironment
        install(HttpTimeout) {
            requestTimeoutMillis = TIMEOUT
            connectTimeoutMillis = TIMEOUT
            socketTimeoutMillis = TIMEOUT
        }

        // Error handling standard
        HttpResponseValidator {
            // untuk response yang status 4xx / 5xx
            validateResponse { res ->
                if (res.status.value >= 300) {
                    val body = try {
                        res.body<ApiError>()
                    } catch (_: Exception) {
                        ApiError("Unknown error", res.status.value)
                    }
                    throw ApiException(res.status.value, body.message ?: "Error")
                }
            }

            // untuk exception non-HTTP
            handleResponseExceptionWithRequest { cause, _ ->
                throw ApiException(-1, cause.message ?: "Unexpected network error")
            }
        }
    }
}