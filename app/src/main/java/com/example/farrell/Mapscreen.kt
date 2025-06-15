package com.example.farrell

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.maps.android.compose.rememberUpdatedMarkerState

//
//import android.Manifest
//import android.content.pm.PackageManager
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.platform.LocalContext
//import androidx.core.content.ContextCompat
//import com.google.android.gms.location.LocationServices
//import com.google.android.gms.maps.model.LatLng
//import com.google.maps.android.compose.*
//
//@Composable
//fun MapScreen() {
//
//    val singapore = LatLng(1.35, 103.87)
//    val cameraPositionState = rememberCameraPositionState {
//        position = CameraPosition.fromLatLngZoom(singapore, 10f)
//    }
//    GoogleMap(
//        modifier = Modifier.fillMaxSize(),
//        cameraPositionState = cameraPositionState
//    )
//
//}
//@Composable
//fun MapScreen() {
//    val context = LocalContext.current
//    var location by remember { mutableStateOf<LatLng?>(null) }
//
//    val permissionGranted = remember {
//        mutableStateOf(
//            ContextCompat.checkSelfPermission(
//                context,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//        )
//    }
//
//    val requestPermissionLauncher = rememberLauncherForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ) { isGranted ->
//        permissionGranted.value = isGranted
//        if (isGranted) {
//            getLastKnownLocation(context) { loc -> location = loc }
//        }
//    }
//
//    LaunchedEffect(Unit) {
//        if (!permissionGranted.value) {
//            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//        } else {
//            getLastKnownLocation(context) { loc -> location = loc }
//        }
//    }
//
//    val cameraPositionState = rememberCameraPositionState {
//        if (location != null) {
//            position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(location!!, 15f)
//        }
//    }
//
//    GoogleMap(
//        cameraPositionState = cameraPositionState,
//        properties = MapProperties(isMyLocationEnabled = location != null)
//    ) {
//        location?.let {
//            Marker(
//                state = MarkerState(position = it),
//                title = "Your location"
//            )
//        }
//    }
//}
//
//private fun getLastKnownLocation(context: android.content.Context, onLocationFound: (LatLng) -> Unit) {
//    val fused = LocationServices.getFusedLocationProviderClient(context)
//    try {
//        fused.lastLocation.addOnSuccessListener { location ->
//            if (location != null) {
//                onLocationFound(LatLng(location.latitude, location.longitude))
//            }
//        }
//    } catch (e: SecurityException) {
//        e.printStackTrace()
//    }
//}



//import androidx.compose.runtime.*
//import androidx.compose.ui.platform.LocalContext
//import android.widget.Toast
//import android.util.Log
//import com.google.maps.android.compose.GoogleMap
//import com.google.maps.android.compose.rememberCameraPositionState
//import com.google.android.gms.maps.model.CameraPosition
//import com.google.android.gms.maps.model.LatLng
//
//
//@Composable
//fun MapScreen() {
//    val context = LocalContext.current
//
//    val error = remember {
//        mutableStateOf<String?>(null)
//    }
//
//    LaunchedEffect(Unit) {
//        try {
//            // Wymuszone wywołanie – błędy pojawią się tylko jeśli np. GoogleMap nie działa poprawnie
//            Log.d("MapScreen", "MapScreen started")
//        } catch (e: Exception) {
//            error.value = e.stackTraceToString()
//        }
//    }
//
//    if (error.value != null) {
//        // Pokazuje błąd jako Toast
//        LaunchedEffect(error.value) {
//            Toast.makeText(context, "MapScreen Error: ${error.value!!.take(200)}", Toast.LENGTH_LONG).show()
//        }
//    }
//
//    // GoogleMap musi być wywoływane *poza* try-catch
//    GoogleMapContent()
//}
//
//@Composable
//fun GoogleMapContent() {
//    val singapore = LatLng(1.35, 103.87)
//    val cameraState = rememberCameraPositionState {
//        position = CameraPosition.fromLatLngZoom(singapore, 10f)
//    }
//
//    GoogleMap(
//        modifier = Modifier.fillMaxSize(),
//        cameraPositionState = cameraState
//    )
//}


@Composable
fun MapScreen() {
    val mapUiSettings by remember{
        mutableStateOf(MapUiSettings(compassEnabled = false))
    }

    val mapProperties by remember{
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }
    val singapore = LatLng(1.35, 103.87)
    val deafultCameraPosition = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(singapore, 4f)
    }

    val locationState = rememberUpdatedMarkerState(
        position = singapore
    )

    GoogleMap(modifier = Modifier,
        onMapLoaded = {

        },
        uiSettings = mapUiSettings,
        properties = mapProperties,
        cameraPositionState = deafultCameraPosition
    ){

        Marker(
            state = locationState,
            draggable = true,
            onClick = {
                return@Marker false
            },
            title = "Map title"
        ){

        }
    }


}

