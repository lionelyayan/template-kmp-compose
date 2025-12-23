package com.sssmobile.fcm

import platform.Foundation.NSUserDefaults

class IOSPushTokenProvider : PushTokenProvider {

    override fun getToken(
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val token =
            NSUserDefaults.standardUserDefaults
                .stringForKey("fcm_token")

        if (token != null) {
            onSuccess(token)
        } else {
            onError(IllegalStateException("FCM token not available"))
        }
    }
}