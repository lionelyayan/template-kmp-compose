package com.sssmobile.fcm

actual fun providePushTokenProvider(): PushTokenProvider {
    return IOSPushTokenProvider()
}