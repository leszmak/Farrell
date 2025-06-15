package com.example.farrell
import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.clickable
import com.example.farrell.AddNumberRoute
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun LockDetailScreen(

    navController: NavController,
    state: State
) {
    val context = LocalContext.current
    val lock = state.lockList.value.find { it.id == state.selectedLock.value }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = lock?.name ?: "Brak nazwy",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                        Column(
                            modifier = Modifier
                                .width(100.dp),
                        ) {
                            Text(text = "${lock?.status}")
                            Text(text = "Bateria: ${lock?.batteryLevel}%")
                            Button(
                                onClick = {navController.navigate(EditLockRoute)},
                                modifier = Modifier.fillMaxWidth()
                            ) { Text(text = "Edytuj") }
                        }
                    }

                    Text(text = "ID: ${lock?.lockid}")
                    Button(onClick = {navController.navigate(MapScreenRoute)}, modifier = Modifier.fillMaxWidth()) { Text(text = "Pokaż lokalizację") }
                    Button(onClick = { navController.navigate(AddNumberRoute) }, modifier = Modifier.fillMaxWidth()) { Text(text = "Dodaj numer") }

//                    Button(onClick = {
//
//                        val phoneNumber = "722183087"
//                        val message = "Uwaga! Próba kradzieży roweru ${lock?.name}. Wiadomość wysłana z aplikacji przez idiotę developera, jeśli nie wiesz o co chodzi to przepraszam, że przeszkadzam"
//                        val result = sendSms(context = context, phoneNumber = phoneNumber, message = message)
//                        val activity = context as Activity
//
//                        requestSmsPermission(activity)
//                        Toast.makeText(context, result, Toast.LENGTH_LONG).show()
//                    }, modifier = Modifier.fillMaxWidth()) { Text(text = "Wyślij sms") }


                    Button(onClick = {


                        val phoneNumber = "722183087"
                        val message = "Uwaga! Próba kradzieży roweru ${lock?.name}. Wiadomość wysłana z aplikacji przez idiotę developera, jeśli nie wiesz o co chodzi toprzepraszam, że przeszkadzam"
                        val result = sendSms(context = context, phoneNumber = phoneNumber, message = message)

                        Toast.makeText(context, result, Toast.LENGTH_LONG).show()
                    }, modifier = Modifier.fillMaxWidth()) { Text(text = "Wyślij sms") }


                    DeleteButtonWithConfirmation(state = state, navController = navController)

                    Text(text = "Numery powiązane z zamkiem:")
                    val numbers = state.getNumbersForSelectedLock()
                    numbers.forEach { item ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = item.name, modifier = Modifier.weight(2f))
                            Text(text = item.number, modifier = Modifier.weight(2f))
                            Text(
                                text = "x",
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { state.removePhoneNumber(item.id) }
                            )
                        }
                    }
                }
            }
            Button(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) { Text(text = "Wróć") }
        }
    }
}

@Composable
fun DeleteButtonWithConfirmation(state: State, navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    // Przycisk "Usuń"
    Button(
        onClick = { showDialog = true },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Usuń")
    }

    // Okno potwierdzenia
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