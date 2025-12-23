package com.sssmobile.api

import com.sssmobile.api.config.ApiConfig.BASE_URL
import com.sssmobile.api.config.UploadFile
import com.sssmobile.api.config.retryRequest
import io.ktor.client.request.*
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.encodeToJsonElement
import kotlin.collections.forEach

object BaseRequest {
    suspend inline fun <reified T> get(
        endpoint: String,
        token: String? = null,
        extraHeaders: Map<String, String> = emptyMap(),
        query: Map<String, Any?> = emptyMap()
    ): BaseResponse<T> = request(HttpMethod.Get, token, extraHeaders, endpoint, query)

    suspend inline fun <reified T> post(
        endpoint: String,
        token: String? = null,
        extraHeaders: Map<String, String> = emptyMap(),
        body: Any? = null,
        query: Map<String, Any?> = emptyMap()
    ): BaseResponse<T> = request(HttpMethod.Post, token, extraHeaders, endpoint, query, body)

    suspend inline fun <reified T> put(
        endpoint: String,
        token: String? = null,
        extraHeaders: Map<String, String> = emptyMap(),
        body: Any? = null,
        query: Map<String, Any?> = emptyMap()
    ): BaseResponse<T> = request(HttpMethod.Put, token, extraHeaders, endpoint, query, body)

    suspend inline fun <reified T> patch(
        endpoint: String,
        token: String? = null,
        extraHeaders: Map<String, String> = emptyMap(),
        body: Any? = null,
        query: Map<String, Any?> = emptyMap()
    ): BaseResponse<T> = request(HttpMethod.Patch, token, extraHeaders, endpoint, query, body)

    suspend inline fun <reified T> delete(
        endpoint: String,
        token: String? = null,
        extraHeaders: Map<String, String> = emptyMap(),
        body: Any? = null,
        query: Map<String, Any?> = emptyMap()
    ): BaseResponse<T> = request(HttpMethod.Delete, token, extraHeaders, endpoint, query, body)

    // ---------- REQUEST JSON UTAMA ----------
    @Suppress("UNCHECKED_CAST")
    suspend inline fun <reified T> request(
        method: HttpMethod,
        token: String? = null,
        extraHeaders: Map<String, String> = emptyMap(),
        endpoint: String,
        query: Map<String, Any?> = emptyMap(),
        body: Any? = null
    ): BaseResponse<T> {
        println("============================ REQUEST ============================")
        println("URL: $BASE_URL$endpoint")
        println("METHOD: $method")
        println("TOKEN: $token")
        println("HEADERS: $extraHeaders")
        println("QUERY: $method")
        println("QUERY: $query")
        println("BODY: $body")
        println("============================ REQUEST ============================")

        return retryRequest {
            val response = ApiClient.client.request(BASE_URL + endpoint) {
                this.method = method
                // Query
                query.forEach { (key, value) ->
                    if (value != null) parameter(key, value)
                }
                // Headers
                headers {
                    // Authorization token (Bearer)
                    if (!token.isNullOrEmpty()) {
                        append("Authorization", "Bearer $token")
                    }

                    // Optional custom headers
                    extraHeaders.forEach { (key, value) ->
                        append(key, value)
                    }
                }
                // JSON body
                if (body != null && method != HttpMethod.Get) {
                    contentType(ContentType.Application.Json)
                    setBody(
                        when (body) {
                            is String -> body
                            is Map<*, *> -> Json.encodeToString(
                                JsonObject.serializer(),
                                JsonObject(mapToJson(body))
                            )
                            is JsonElement -> Json.encodeToString(JsonElement.serializer(), body)
                            else -> Json.encodeToString(Json.encodeToJsonElement(body))
                        }
                    )
                }
            }

            val responseJson: JsonElement = response.body()

            println("============================ RESPONSE ============================")
            println(responseJson)
            println("============================ RESPONSE ============================")

            wrapAsBaseResponse<T>(
                raw = responseJson,
                code = response.status.value,
                message = response.status.description
            )
        }
    }

    // ================================================
    // MULTIPART
    // ================================================
    suspend inline fun <reified T> uploadMultipart(
        endpoint: String,
        fields: Map<String, Any?> = emptyMap(),
        files: List<UploadFile> = emptyList()
    ): T {

        return retryRequest {
            ApiClient.client.submitFormWithBinaryData(
                url = BASE_URL + endpoint,
                formData = formData {

                    // Add form fields
                    fields.forEach { (key, value) ->
                        if (value != null) {
                            append(key, value.toString())
                        }
                    }

                    // Add files
                    files.forEach { file ->
                        append(
                            key = file.fieldName,
                            value = file.bytes,
                            headers = Headers.build {
                                append(
                                    HttpHeaders.ContentDisposition,
                                    "form-data; name=\"${file.fieldName}\"; filename=\"${file.fileName}\""
                                )
                                append(HttpHeaders.ContentType, file.mimeType)
                            }
                        )
                    }
                }
            ).body()
        }
    }
}

fun mapToJson(map: Map<*, *>): Map<String, JsonElement> {
    return map.entries.associate { (key, value) ->
        val k = key.toString()
        val v = when (value) {
            null -> JsonNull
            is Number -> JsonPrimitive(value)
            is Boolean -> JsonPrimitive(value)
            is String -> JsonPrimitive(value)
            is Map<*, *> -> JsonObject(mapToJson(value))
            else -> JsonPrimitive(value.toString())
        }
        k to v
    }
}