package com.example.farrell

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

val locks = listOf(
    Lock(1, "Front aaaaaaaaaaaa aaaaaaaaaaaaaaaaa aaaaaaaaaa aaaaa", status = LockStatus.Activated, batteryLevel = 78),
    Lock(2, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
    Lock(3, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
    Lock(4, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
    Lock(5, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
    Lock(6, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
    Lock(7, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
    Lock(8, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
    Lock(9, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
    Lock(10, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
    Lock(11, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
    Lock(12, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
    Lock(13, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
    Lock(14, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
    Lock(15, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
    Lock(16, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
    Lock(17, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
    Lock(18, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
    Lock(19, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
    Lock(20, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
    Lock(21, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
    Lock(22, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
    Lock(23, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
    Lock(24, "Garage", status = LockStatus.Deactivated, batteryLevel = 52),
    Lock(25, "Front Door", status = LockStatus.Activated, batteryLevel = 78),
    Lock(26, "Garage", status = LockStatus.Stolen, batteryLevel = 52),
)

@Composable
fun MainApp(modifier: Modifier = Modifier, onLockClick: (Lock) -> Unit, onAddLockClick: () -> Unit) {

    Column {
        Button(onClick = onAddLockClick, modifier.fillMaxWidth()) { Text("Dodaj zabezpieczenie") }
        LazyColumn {
            items(locks) { lock ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { onLockClick(lock) },
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

