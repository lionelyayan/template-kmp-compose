package com.sssmobile.helpers.deeplink

data class DeeplinkEvent(
    val deeplink: String,
    val userData: String? = null
)
