package com.sssmobile.models

import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    val id: Int,
    val name: String,
    val username: String,
    val email: String
)