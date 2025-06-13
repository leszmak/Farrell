package com.example.farrell

import androidx.compose.runtime.mutableStateOf


class State {
    val locks = listOf(
        Lock(1, "Front aaaaaaaaaaaa aaaaaaaaaaaaaaaaa aaaaaaaaaa aaaaa", status = LockStatus.Activated, batteryLevel = 78),
        Lock(2, "Garage22222", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(3, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
        Lock(4, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(5, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//        Lock(6, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//        Lock(7, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//        Lock(8, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//        Lock(9, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//        Lock(10, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//        Lock(11, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//        Lock(12, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//        Lock(13, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//        Lock(14, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//        Lock(15, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//        Lock(16, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//        Lock(17, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//        Lock(18, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//        Lock(19, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//        Lock(20, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//        Lock(21, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//        Lock(22, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//        Lock(23, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//        Lock(24, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//        Lock(25, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//        Lock(26, "Garage", status = LockStatus.Stolen, batteryLevel = 52),
    )
    val phoneNumbers = listOf(
        PhoneNumber(id = 1, lockId = 1, number = "+48722183087", name = "Mama"),
        PhoneNumber(id = 2, lockId = 1, number = "+48722183087", name = "Tata"),
        PhoneNumber(id = 3, lockId = 2, number = "+48722183087", name = "Praca"),
        PhoneNumber(id = 4, lockId = 2, number = "+48722183087", name = "Przyjaciel"),
        PhoneNumber(id = 5, lockId = 3, number = "+48722183087", name = "Serwis"),
        PhoneNumber(id = 6, lockId = 3, number = "+48722183087", name = "Dziadek"),
        PhoneNumber(id = 7, lockId = 4, number = "+48722183087", name = "Babcia"),
        PhoneNumber(id = 8, lockId = 4, number = "+48722183087", name = "Szef"),
        PhoneNumber(id = 9, lockId = 5, number = "+48722183087", name = "Przyjaciel 2"),
        PhoneNumber(id = 10, lockId = 5, number = "+48722183087", name = "Serwis 2")
    )

//    var lockList = mutableStateOf(listOf<Lock>())
//    var lockList = mutableStateOf(listOf<PhoneNumber>())

    var lockList = mutableStateOf(locks)
    var phoneNumbersList = mutableStateOf(phoneNumbers)


    var selectedLock = mutableStateOf(1)

    fun getNumbersForSelectedLock(): List<PhoneNumber> {
        return phoneNumbersList.value.filter { it.lockId == selectedLock.value }
    }

    fun addPhoneNumber(name: String, number: String): String {
        if (name.isBlank()) {
            return "Nazwa nie może być pusta"
        }

        if (number.length != 9) {
            return "Numer musi mieć dokładnie 9 cyfr"
        }

        if (!number.all { it.isDigit() }) {
            return "Numer może zawierać tylko cyfry"
        }

        val nextId = (phoneNumbersList.value.maxOfOrNull { it.id } ?: 0) + 1
        val newPhoneNumber = PhoneNumber(
            id = nextId,
            lockId = selectedLock.value,
            name = name,
            number = number
        )

        phoneNumbersList.value = phoneNumbersList.value + newPhoneNumber
        return "" // pusta wartość oznacza sukces
    }

    fun removePhoneNumber(id: Int): Boolean {
        val currentList = phoneNumbersList.value
        val newList = currentList.filter { it.id != id }

        return if (newList.size < currentList.size) {
            phoneNumbersList.value = newList
            true
        } else {
            false
        }
    }

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