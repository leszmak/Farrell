package com.example.farrell

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.farrell.ui.theme.FarrellTheme
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data object LockListRoute

@Serializable
data object LockDetailRoute

@Serializable
data object AddLockRoute

@Serializable
data object AddNumberRoute

@Serializable
data object LocationRoute

@Serializable
data object MapScreenRoute

@Serializable
data object EditLockRoute

class MainActivity : ComponentActivity() {

//    private val SMS_PERMISSION_REQUEST_CODE = 1001
    private val LOCATION_PERMISSION_REQUEST_CODE = 1002

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Prośba o uprawnienie
//        checkAndRequestPermissions(
//            requestCode = SMS_PERMISSION_REQUEST_CODE,
//            permissions = arrayOf(Manifest.permission.SEND_SMS)
//        )

//        checkAndRequestPermissions(
//            requestCode = LOCATION_PERMISSION_REQUEST_CODE,
//            permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
//        )

        val requiredPermissions = arrayOf(
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_PHONE_NUMBERS,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET
        )

        checkAndRequestPermissions(requestCode = 1000, permissions = requiredPermissions)



        enableEdgeToEdge()
        setContent {
            val state = remember { State() }
//            GetPermissions(this)
            FarrellTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    GetPermissions(this)
                    NavHost(
                        navController = navController,
                        startDestination = LockListRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<LockListRoute> {
                            LockListScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                state = state
                            )
                        }

                        composable<LockDetailRoute>{
                            LockDetailScreen(
                                navController = navController,
                                state = state
                            )
                        }

                        composable<AddLockRoute> {
                            AddLockScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                state = state
                            )
                        }

                        composable<AddNumberRoute> {
                            AddNumber(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                state = state
                            )
                        }

                        composable<LocationRoute> {
                            LocationScreen(
                                state = state,
                                navController = navController
                            )
                        }

                        composable<MapScreenRoute> {
                            MapScreen(
                            )
                        }

                        composable<EditLockRoute> {
                            EditLockScreen(
                                state = state,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }

    fun checkAndRequestPermissions(requestCode: Int, permissions: Array<String>) {
        val missingPermissions = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (missingPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                missingPermissions.toTypedArray(),
                requestCode
            )
        }
    }
}

