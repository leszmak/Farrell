package com.example.farrell

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


class State {
    val locks = listOf(
        Lock(1, "lockid1", "Front aaaaaaaaaaaa aaaaaaaaaaaaaaaaa aaaaaaaaaa aaaaa", status = LockStatus.Activated, batteryLevel = 78),
        Lock(2, "lockid2", "Garage22222", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(3, "lockid3", "Front Door", status = LockStatus.Activated, batteryLevel = 78),
        Lock(4, "lockid4", "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
        Lock(5, "lockid5", "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//    Lock(6, "lockid6", "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//    Lock(7, "lockid7", "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//    Lock(8, "lockid8", "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//    Lock(9, "lockid9", "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//    Lock(10, "lockid10", "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//    Lock(11, "lockid11", "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//    Lock(12, "lockid12", "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//    Lock(13, "lockid13", "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//    Lock(14, "lockid14", "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//    Lock(15, "lockid15", "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//    Lock(16, "lockid16", "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//    Lock(17, "lockid17", "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//    Lock(18, "lockid18", "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//    Lock(19, "lockid19", "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//    Lock(20, "lockid20", "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//    Lock(21, "lockid21", "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//    Lock(22, "lockid22", "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//    Lock(23, "lockid23", "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//    Lock(24, "lockid24", "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
//    Lock(25, "lockid25", "Front Door", status = LockStatus.Activated, batteryLevel = 78),
//    Lock(26, "lockid26", "Garage", status = LockStatus.Stolen, batteryLevel = 52),
    )

    val phoneNumbers = listOf(
        PhoneNumber(id = 1, lockId = 1, number = "722183087", name = "Mama"),
        PhoneNumber(id = 2, lockId = 1, number = "722183087", name = "Tata"),
        PhoneNumber(id = 3, lockId = 2, number = "722183087", name = "Praca"),
        PhoneNumber(id = 4, lockId = 2, number = "722183087", name = "Przyjaciel"),
        PhoneNumber(id = 5, lockId = 3, number = "722183087", name = "Serwis"),
        PhoneNumber(id = 6, lockId = 3, number = "722183087", name = "Dziadek"),
        PhoneNumber(id = 7, lockId = 4, number = "722183087", name = "Babcia"),
        PhoneNumber(id = 8, lockId = 4, number = "722183087", name = "Szef"),
        PhoneNumber(id = 9, lockId = 5, number = "722183087", name = "Przyjaciel 2"),
        PhoneNumber(id = 10, lockId = 5, number = "722183087", name = "Serwis 2")
    )

//    var lockList = mutableStateOf(listOf<Lock>())
//    var lockList = mutableStateOf(listOf<PhoneNumber>())

    var lockList = mutableStateOf(locks)
    var phoneNumbersList = mutableStateOf(phoneNumbers)

    var newlockname =  mutableStateOf("")
    var newlockid = mutableStateOf("")
    var status = mutableStateOf(LockStatus.Deactivated)
    var batteryLevel = mutableStateOf(100)

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

    fun addLock(): String {
        val name = newlockname.value.trim()
        val lockid = newlockid.value.trim()

        if (name.isEmpty()) return "Podaj nazwę zabezpieczenia"
        if (lockid.isEmpty()) return "Podaj ID zabezpieczenia"

        val id = (lockList.value.maxOfOrNull { it.id } ?: 0) + 1
        val newLock = Lock(
            id = id,
            name = name,
            status = LockStatus.Deactivated,
            batteryLevel = 100,
            lockid = lockid
        )

        lockList.value = lockList.value + newLock
        return "Dodano zabezpieczenie"
    }

    fun getSelectedLock(): Lock? {
        return lockList.value.find { it.id == selectedLock.value }
    }

    fun deleteSelectedLock(): String {
        val lockToDelete = lockList.value.find { it.id == selectedLock.value }
        return if (lockToDelete != null) {
            lockList.value = lockList.value - lockToDelete
            "Usunięto zabezpieczenie"
        } else {
            "Nie znaleziono zabezpieczenia do usunięcia"
        }
    }

    fun editSelectedLock(updatedLock: Lock): String {
        val currentList = lockList.value
        val oldLock = currentList.find { it.id == updatedLock.id }

        if (oldLock == null) {
            return "Nie znaleziono zabezpieczenia do edycji"
        }
        if (updatedLock.name.isEmpty()) return "Nazwa nie może być pusta"
        if (updatedLock.lockid.isEmpty()) return "Id nie może być pusty"

        // Porównaj czy coś się zmieniło
        if (
            oldLock.name == updatedLock.name &&
            oldLock.lockid == updatedLock.lockid &&
            oldLock.status == updatedLock.status &&
            oldLock.batteryLevel == updatedLock.batteryLevel
        ) {
            return "Brak zmian do zapisania"
        }

        // Aktualizacja: usuń stary, dodaj nowy
        lockList.value = currentList - oldLock + updatedLock

        return "Zabezpieczenie zaktualizowane"
    }

//    fun resetSelectedLock(){
//        newlockname.value = "newLock"
//        newlockid.value = "newLock"
//        status.value = LockStatus.Deactivated
//        batteryLevel.value = 100
//    }



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