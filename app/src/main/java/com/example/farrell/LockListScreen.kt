package com.example.farrell

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LockListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: State
) {

    Column {
        Button(onClick = {navController.navigate(AddLockRoute)}, modifier.fillMaxWidth()) { Text("Dodaj zabezpieczenie") }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Nazwa", modifier = Modifier.weight(1f))
            Text(text = "Status", modifier = Modifier.weight(1f))
            Text(text = "Bateria", modifier = Modifier.weight(1f))
        }

        LazyColumn {
            items(state.lockList.value) { lock ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            state.selectedLock.value = lock.id
                            navController.navigate(LockDetailRoute)
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = lock.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = "${lock.status}", modifier = Modifier.weight(1f))
                    Text(text = "${lock.batteryLevel}", modifier = Modifier.weight(1f))
                }

            }
        }
    }
}

