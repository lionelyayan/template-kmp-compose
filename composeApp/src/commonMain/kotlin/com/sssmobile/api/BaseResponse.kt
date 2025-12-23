package com.sssmobile.api

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
data class BaseResponse<T>(
    val code: Int,
    val message: String,
    val data: T? = null
)

// Fungsi untuk membungkus response API apa pun (dynamic)
inline fun <reified T> wrapAsBaseResponse(raw: JsonElement, code: Int, message: String): BaseResponse<T> {
    val json = Json { ignoreUnknownKeys = true }

    val parsed: T? = when (T::class) {
        JsonElement::class -> raw as T
        else -> json.decodeFromJsonElement(raw)
    }

    return BaseResponse(
        code = code,
        message = message,
        data = parsed
    )
}
