package com.example.farrell
import kotlinx.serialization.Serializable

@Serializable
data class Lock(
    val id: Int,
    val lockid: String,
    val name: String,
    val status: LockStatus,
    val batteryLevel: Int
)
// enum Lock
enum class LockStatus {
    Activated,
    Deactivated,
    Stolen
}