package com.sssmobile.data.repository

import com.sssmobile.api.BaseRequest
import com.sssmobile.api.BaseResponse
import com.sssmobile.models.ResponseModel
import kotlinx.coroutines.delay

class Test {

    private var nextActivity = false

    suspend fun isNextActivity(): Boolean {
        delay(3000)
        return nextActivity
    }

    suspend fun login(email: String, password: String): Boolean {
        delay(1000)
        val success = email == "admin@mail.com" && password == "admin"
        if (success) nextActivity = true
        return success
    }

    suspend fun getUserDetailTest(id: Int): BaseResponse<ResponseModel> {
        return BaseRequest.get(
            endpoint = "/users/$id",
            token = "---",
            extraHeaders = mapOf(
                "X-App-Version" to "1.0.0",
                "X-Device" to "KMP"
            )
        )
    }
}
