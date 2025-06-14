package com.example.farrell

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.farrell.State
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

@Composable
fun LocationScreen(
    state: State,
    navController: NavHostController
) {
    val context = LocalContext.current
    var locationText by remember { mutableStateOf("Pobieranie lokalizacji...") }

    LaunchedEffect(Unit) {
        val location = getLastKnownLocation(context)
        locationText = if (location != null) {
            "Szerokość: ${location.latitude}, Długość: ${location.longitude}"
        } else {
            "Nie udało się uzyskać lokalizacji"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = locationText)

        Button(onClick = { navController.popBackStack() }) {
            Text("Wróć")
        }
    }
}

@SuppressLint("MissingPermission")
fun getLastKnownLocation(context: Context): Location? {
    if (
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED
    ) {
        return null
    }

    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val providers = locationManager.getProviders(true)

    for (provider in providers.reversed()) {
        val location = locationManager.getLastKnownLocation(provider)
        if (location != null) {
            return location
        }
    }

    return null
}

