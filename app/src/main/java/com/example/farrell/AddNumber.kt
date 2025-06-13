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
import androidx.navigation.NavController

@Composable
fun AddNumber(
    modifier: Modifier,
    navController: NavController,
    state: State
) {
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

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
                    placeholder = { Text("Wprowadź nazwę") }
                )

                Text(text = "Numer:")
                TextField(
                    value = number,
                    onValueChange = { number = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Wprowadź numer") }
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = androidx.compose.ui.graphics.Color.Red,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        Button(
            onClick = {
                val result = state.addPhoneNumber(name = name, number = number)
                if (result.isEmpty()) {
                    navController.popBackStack()
                } else {
                    errorMessage = result
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Dodaj")
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Wróć")
        }
    }
}

