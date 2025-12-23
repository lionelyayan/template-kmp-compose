package com.sssmobile.api.config

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.io.IOException

open class BaseRepository {
    suspend fun <T> safeApiCall(
        block: suspend () -> T
    ): ApiResult<T> = withContext(Dispatchers.IO) {
        try {
            val response = block()
            ApiResult.Success(response)

        } catch (e: ApiException) {
            ApiResult.Error(
                message = e.message,
                code = e.httpCode,
                throwable = e
            )

        } catch (e: IOException) {
            ApiResult.Error(
                message = "Koneksi gagal. Periksa internet Anda.",
                throwable = e
            )

        } catch (e: Exception) {
            ApiResult.Error(
                message = e.message ?: "Terjadi kesalahan.",
                throwable = e
            )
        }
    }
}
