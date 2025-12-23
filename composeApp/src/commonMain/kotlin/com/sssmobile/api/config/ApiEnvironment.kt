package com.sssmobile.api.config

enum class ApiEnvironment(val baseUrl: String) {
    DEV("https://jsonplaceholder.typicode.com/"),
    STAGING("https://jsonplaceholder.typicode.com/"),
    PROD("https://jsonplaceholder.typicode.com/")
}

const val TIMEOUT = 20_000L   // 20 detik timeout
const val RETRY_COUNT = 3     // retry 3x
const val DELAY = 500L        // delay retry 0.5 detik