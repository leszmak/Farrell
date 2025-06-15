package com.example.farrell

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextField
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext


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
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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

        Text("Status:")
        DropdownMenuBox(

            selectedStatus = status,
            onStatusChange = { status = it }
        )

        Text("Poziom baterii:")
        TextField(
            value = batteryLevel,
            onValueChange = { batteryLevel = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )


//        sprawdź czy batteryLevel jest sprowadzalny do int i czy jest z zakresu 0-100
        Button(
            onClick = {
                val level = batteryLevel.toIntOrNull()
                if (level == null || level !in 0..100) {
                    val a ="Poziom baterii musi być liczbą z zakresu 0-100"
                    Toast.makeText(context, a, Toast.LENGTH_SHORT).show()
                    return@Button
                }

                var updatedLock = Lock(id = state.selectedLock.value,
                    name = name,
                    lockid = lockid,
                    status = status,
                    batteryLevel = batteryLevel.toInt()
                )
                val result = state.editSelectedLock(updatedLock)
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                if("Zabezpieczenie zaktualizowane" == result) navController.popBackStack()
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



