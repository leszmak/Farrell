package com.example.farrell

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AddLockScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: State
) {
    val context = LocalContext.current

    var lockid by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var batteryLevel by remember { mutableStateOf("100") }
    var status by remember { mutableStateOf(LockStatus.Deactivated) }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFF0D47A1), Color(0xFF42A5F5)),
                        start = Offset(0f, 0f),
                        end = Offset.Infinite
                    )
                )
                .padding(16.dp)
        ) {
            Text(
                text = "Dodaj zabezpieczenie",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "ID:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            TextField(
                value = lockid,
                onValueChange = { lockid = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Wprowadź ID") }
            )

            Text(
                text = "Nazwa:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            TextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Wprowadź nazwę") }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Status:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                DropdownMenuBox(
                    modifier = Modifier,
                    selectedStatus = status,
                    onStatusChange = { status = it }
                )
            }

            Text(
                text = "Poziom baterii:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            TextField(
                value = batteryLevel,
                onValueChange = { batteryLevel = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("0–100") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Button(
                onClick = {
                    val battery = batteryLevel.toIntOrNull()
                    if (battery == null || battery !in 0..100) {
                        Toast.makeText(
                            context,
                            "Poziom baterii musi być liczbą 0–100",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@Button
                    }

                    val lock = Lock(
                        id = -1,
                        name = name,
                        lockid = lockid,
                        status = status,
                        batteryLevel = battery
                    )

                    val message = state.addLock(lock)
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    if (message == "Dodano zabezpieczenie") {
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Zapisz")
            }

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Wróć")
            }
        }
    }
}

