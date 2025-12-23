package com.sssmobile.viewModels

import com.sssmobile.fcm.PushTokenRepository
import com.sssmobile.viewModels.baseViewModel.BaseViewModel

class FcmViewModel(
    private val fcmRepo: PushTokenRepository
) : BaseViewModel() {

    fun fetchFcmToken() {
        fcmRepo.fetchToken(
            onTokenReady = { token ->
                println("PUSH TOKEN: $token")
            },
            onError = { error ->
                println("Failed to get push token: $error")
            }
        )
    }
}