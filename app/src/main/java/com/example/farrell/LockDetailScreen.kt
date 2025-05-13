package com.example.farrell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun LockDetailScreen(
    lock: Lock,
    onBackClick: () -> Unit,
    onAddNumberClick: (Lock) -> Unit
) {
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
                            text = lock.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                        Column(
                            modifier = Modifier
                                .width(100.dp),
                        ) {
                            Text(text = "${lock.status}")
                            Text(text = "Bateria: ${lock.batteryLevel}%")
                            Button(
                                onClick = {},
                                modifier = Modifier.fillMaxWidth()
                            ) { Text(text = "Edytuj") }
                        }
                    }

                    Text(text = "ID: ${lock.id}")
                    Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text(text = "Pokaż lokalizację") }
                    Button(onClick = {onAddNumberClick(lock)}, modifier = Modifier.fillMaxWidth()) { Text(text = "Dodaj numer") }
                    Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text(text = "Ping zamka") }

                    if (lock.status == LockStatus.Activated) {
                        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text(text = "Wyłącz zamek") }
                    }
                    if (lock.status == LockStatus.Deactivated) {
                        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text(text = "Włącz zamek") }
                    }

                    Text(text = "Numery powiązane z zamkiem:")
                }
            }
            Button(onClick = { onBackClick() }, modifier = Modifier.fillMaxWidth()) { Text(text = "Wróć") }
        }
    }
}

