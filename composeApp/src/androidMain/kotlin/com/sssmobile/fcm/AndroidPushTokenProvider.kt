package com.sssmobile.fcm

import com.google.firebase.messaging.FirebaseMessaging

class AndroidPushTokenProvider : PushTokenProvider {

    override fun getToken(
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        FirebaseMessaging.getInstance().token
            .addOnSuccessListener { token ->
                onSuccess(token)
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }
}