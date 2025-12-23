package com.sssmobile.api.config

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val message: String? = null,
    val code: Int? = null
)