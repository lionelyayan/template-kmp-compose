package com.sssmobile

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform