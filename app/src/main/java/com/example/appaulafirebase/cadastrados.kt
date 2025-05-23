package com.example.appaulafirebase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items

@Composable
fun ListaCadastrados(
    proximo: () -> Unit = {},
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            Arrangement.Center
        ) {
            Text("Em desenvolvimento")
            Row(
                modifier = Modifier
                    .fillMaxWidth(), Arrangement.Center
            ) {
                Button(onClick = proximo ) {
                    Text("Voltar")
                }
            }
        }
    }
}