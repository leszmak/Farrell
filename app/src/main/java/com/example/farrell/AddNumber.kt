package com.example.farrell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

@Composable
fun AddNumber(modifier: Modifier,lock: Lock, onBackClick: (Lock) -> Unit) {
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(text = "ID:")
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Wprowadź ID") }
                )


                Text(text = "Numer:")
                TextField(
                    value = number,
                    onValueChange = { number = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Wprowadź nazwę") }
                )

            }
        }
        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text(text = "Dodaj") }
        Button(onClick = { onBackClick(lock) }, modifier = Modifier.fillMaxWidth()) { Text(text = "Wróć") }
    }
}
