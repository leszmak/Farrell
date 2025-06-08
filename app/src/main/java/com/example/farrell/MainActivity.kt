package com.example.farrell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
data class LockDetailRoute(
    val lock: Lock
)

@Serializable
data object AddLockRoute

@Serializable
data class AddNumberRoute(
    val lock: Lock
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FarrellTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = LockListRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<LockListRoute> {
                            LockListScreen(
                                modifier = Modifier.padding(innerPadding),
                                onLockClick = { lock ->
                                    navController.navigate(LockDetailRoute(lock = lock))
                                },
                                onAddLockClick = { navController.navigate(AddLockRoute) })
                        }

                        composable<LockDetailRoute>(
                            typeMap = mapOf(
                                typeOf<Lock>() to CustomNavType.LockType
                            )
                        ) {
                            val arguments = it.toRoute<LockDetailRoute>()
                            LockDetailScreen(
                                lock = arguments.lock,
                                onBackClick = { navController.navigate(LockListRoute) },
                                onAddNumberClick = { lock ->
                                    navController.navigate(AddNumberRoute(lock = lock))
                                }
                            )
                        }

                        composable<AddLockRoute> {
                            AddLockScreen(
                                modifier = Modifier.padding(innerPadding),
                                onBackClick = {
                                    navController.navigate(LockListRoute)
                                })
                        }

                        composable<AddNumberRoute>(
                            typeMap = mapOf(
                                typeOf<Lock>() to CustomNavType.LockType
                            )
                        ) {
                            val arguments = it.toRoute<AddNumberRoute>()
                            AddNumber(
                                modifier = Modifier.padding(innerPadding),
                                lock = arguments.lock,
                                onBackClick = { lock ->
                                    navController.navigate(LockDetailRoute(lock = lock))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

