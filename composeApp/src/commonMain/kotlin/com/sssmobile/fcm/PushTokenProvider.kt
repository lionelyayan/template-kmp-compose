package com.sssmobile.fcm

interface PushTokenProvider {
    fun getToken(
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    )
}