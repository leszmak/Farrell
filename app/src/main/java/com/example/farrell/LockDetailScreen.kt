package com.example.farrell
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon

@Composable
fun LockDetailScreen(
    navController: NavController,
    state: State
) {
    val context = LocalContext.current
    val lock = state.lockList.value.find { it.id == state.selectedLock.value }

    Column(modifier = Modifier.fillMaxSize()) {

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
                text = "Szczegóły zabezpieczenia",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            DetailRow(label = "ID:", value = lock?.lockid ?: "")
            DetailRow(label = "Nazwa:", value = lock?.name ?: "")
            DetailRow(label = "Status:", value = lock?.status.toString())
            DetailRow(label = "Bateria:", value = "${lock?.batteryLevel}%")

            Button(
                onClick = { navController.navigate(EditLockRoute) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Edytuj")
            }

            Button(
                onClick = { navController.navigate(MapScreenRoute) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pokaż lokalizację")
            }

            Button(
                onClick = { navController.navigate(AddNumberRoute) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Dodaj numer")
            }

            Button(
                onClick = {
                    val phoneNumbers = state.getNumbersForSelectedLock()
                    val result = ""
                    phoneNumbers.forEach{number ->
                        val message =
                            "Uwaga! Próba kradzieży roweru ${lock?.name}."
                        sendSms(context = context, phoneNumber = number.number, message = message)
                    }

                    Toast.makeText(context, result, Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Wyślij sms")
            }

            DeleteButtonWithConfirmation(state = state, navController = navController)

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ) {
                Text("Wróć")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE3F2FD))
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = "Nazwa",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Numer",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Usuń",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(0.5f),
                    textAlign = TextAlign.Center
                )
            }

            Divider(color = Color.Gray, thickness = 1.dp)

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(state.getNumbersForSelectedLock()) { item ->
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = item.name,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = item.number,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Usuń numer",
                                tint = Color.Red,
                                modifier = Modifier
                                    .weight(0.5f)
                                    .size(24.dp)
                                    .clickable { state.removePhoneNumber(item.id) }
                            )
                        }
                        Divider(color = Color.LightGray, thickness = 0.5.dp)
                    }
                }
            }
        }
    }
}

@Composable
fun DeleteButtonWithConfirmation(state: State, navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Button(
        onClick = { showDialog = true },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Usuń")
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Potwierdzenie") },
            text = { Text("Czy na pewno chcesz usunąć ten zamek?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        state.deleteSelectedLock()
                        Toast.makeText(context, "Usunięto zabezpieczenie", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                ) {
                    Text("Tak")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text("Nie")
                }
            }
        )
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.End
        )
    }
}