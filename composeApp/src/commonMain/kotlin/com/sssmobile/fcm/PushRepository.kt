package com.sssmobile.fcm

class PushTokenRepository(
    private val provider: PushTokenProvider
) {

    fun fetchToken(
        onTokenReady: (String) -> Unit,
        onError: (Throwable) -> Unit = {}
    ) {
        provider.getToken(
            onSuccess = { token ->
                onTokenReady(token)
            },
            onError = onError
        )
    }
}