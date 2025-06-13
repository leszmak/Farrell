package com.example.farrell

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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val state = remember { State() }
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
                    }
                }
            }
        }
    }
}

