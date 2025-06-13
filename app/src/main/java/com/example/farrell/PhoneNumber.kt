package com.example.farrell

import kotlinx.serialization.Serializable

@Serializable
data class PhoneNumber(
    val id: Int,
    val lockId: Int,
    val number: String,
    val name: String
)