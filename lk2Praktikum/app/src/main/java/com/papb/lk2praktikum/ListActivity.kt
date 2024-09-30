package com.papb.lk2praktikum

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirestoreDataDisplay()
        }
    }
}

@Composable
fun FirestoreDataDisplay() {
    val db = Firebase.firestore
    var dataList by remember { mutableStateOf(listOf<Map<String, Any>>()) }

    // Mengambil data dari Firestore
    LaunchedEffect(Unit) {
        val result = db.collection("matkul").get().await()
        dataList = result.documents.map { it.data ?: emptyMap() }
    }

    // Menampilkan data menggunakan LazyColumn dan CardView
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(dataList) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Hari: ${item["Hari"] ?: "Unknown"}")
                    Text(text = "Jam: ${item["Jam"] ?: "Unknown"}")
                    Text(text = "Mata Kuliah: ${item["MatKul"] ?: "Unknown"}")
                    Text(text = "Kode: ${item["Kode"] ?: "Unknown"}")
                }
            }
        }
    }
}


