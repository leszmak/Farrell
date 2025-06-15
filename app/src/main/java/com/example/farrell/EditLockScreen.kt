package com.example.farrell

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextField
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun EditLockScreen(
    state: State,
    navController: NavController
) {
    val originalLock = state.getSelectedLock() ?: return
    val context = LocalContext.current

    var lockid by remember { mutableStateOf(originalLock.lockid) }
    var name by remember { mutableStateOf(originalLock.name) }
    var status by remember { mutableStateOf(originalLock.status) }
    var batteryLevel by remember { mutableStateOf(originalLock.batteryLevel.toString()) }

    Column(
        modifier = Modifier.fillMaxSize()
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
                text = "Edycja zabezpieczenia",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Lock ID:")
                TextField(
                    value = lockid,
                    onValueChange = { lockid = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Text("Nazwa:")
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Status:", modifier = Modifier.weight(1f))
                    DropdownMenuBox(
                        selectedStatus = status,
                        onStatusChange = { status = it },
                        modifier = Modifier
                    )
                }

                Text("Poziom baterii:")
                TextField(
                    value = batteryLevel,
                    onValueChange = { batteryLevel = it },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Button(
                onClick = {
                    val level = batteryLevel.toIntOrNull()
                    if (level == null || level !in 0..100) {
                        Toast.makeText(context, "Poziom baterii musi być liczbą z zakresu 0-100", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val updatedLock = Lock(
                        id = state.selectedLock.value,
                        name = name,
                        lockid = lockid,
                        status = status,
                        batteryLevel = level
                    )
                    val result = state.editSelectedLock(updatedLock)
                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                    if ("Zabezpieczenie zaktualizowane" == result) navController.popBackStack()
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




