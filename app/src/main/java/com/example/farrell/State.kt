package com.example.farrell

import androidx.compose.runtime.mutableStateOf


class State {
    val locks = listOf(
        Lock(1, "Front aaaaaaaaaaaa aaaaaaaaaaaaaaaaa aaaaaaaaaa aaaaa", status = LockStatus.Activated, batteryLevel = 78),
        Lock(2, "Garage22222", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(3, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
        Lock(4, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(5, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
        Lock(6, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(7, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
        Lock(8, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(9, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
        Lock(10, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(11, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
        Lock(12, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(13, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
        Lock(14, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(15, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
        Lock(16, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(17, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
        Lock(18, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(19, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
        Lock(20, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(21, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
        Lock(22, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(23, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
        Lock(24, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(25, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
        Lock(26, "Garage", status = LockStatus.Stolen, batteryLevel = 52),
    )
//    var lockList = mutableStateOf(listOf<Lock>())
    var lockList = mutableStateOf(locks)
    var selectedLock = mutableStateOf<Int?>(null)
//        private set


//    fun addLock(lock: Lock) {
//        lockList = lockList + lock
//    }
//
//    fun removeLock(lock: Lock) {
//        lockList = lockList - lock
//    }
//
//    fun clearLocks() {
//        lockList = emptyList()
//    }
}