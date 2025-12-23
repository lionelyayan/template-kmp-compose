package com.sssmobile.api.config

class ApiException(
    val httpCode: Int,
    override val message: String
) : Exception(message)