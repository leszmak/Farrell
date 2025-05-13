package com.example.farrell

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


object CustomNavType {
    val LockType = object : NavType <Lock>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): Lock? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Lock {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Lock): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: Lock) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}