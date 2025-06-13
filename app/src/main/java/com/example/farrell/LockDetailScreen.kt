package com.example.farrell
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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun LockDetailScreen(

    navController: NavController,
    state: State
) {
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
                                onClick = {},
                                modifier = Modifier.fillMaxWidth()
                            ) { Text(text = "Edytuj") }
                        }
                    }

                    Text(text = "ID: ${lock?.id}")
                    Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text(text = "Pokaż lokalizację") }
                    Button(onClick = { navController.navigate(AddNumberRoute) }, modifier = Modifier.fillMaxWidth()) { Text(text = "Dodaj numer") }
                    Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text(text = "Ping zamka") }

                    if (lock?.status == LockStatus.Activated) {
                        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text(text = "Wyłącz zamek") }
                    }
                    if (lock?.status == LockStatus.Deactivated) {
                        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text(text = "Włącz zamek") }
                    }

                    Text(text = "Numery powiązane z zamkiem:")
                    val numbers = state.getNumbersForSelectedLock()
                    numbers.forEach { item ->
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = item.name, modifier = Modifier.weight(1f))
                            Text(text = item.number, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
            Button(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) { Text(text = "Wróć") }
        }
    }
}

