package com.papb.lk2praktikum

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.LineHeightStyle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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

        // Floating Action Button (FAB) di pojok kanan bawah
        val context = LocalContext.current
        FloatingActionButton(
            onClick = {
                val intent = Intent(context, GithubProfileActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.github),
                contentDescription = "Github Icon",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewFirestoreDataDisplay() {
    // Dummy data untuk preview
    val dummyData = listOf(
        mapOf("Hari" to "Senin", "Jam" to "08:00", "MatKul" to "Matematika", "Kode" to "MATH101"),
        mapOf("Hari" to "Selasa", "Jam" to "10:00", "MatKul" to "Fisika", "Kode" to "PHYS101")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(dummyData) { item ->
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

        // Floating Action Button (FAB) di pojok kanan bawah
        val context = LocalContext.current
        FloatingActionButton(
            onClick = {
                val intent = Intent(context, GithubProfileActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.github),
                contentDescription = "Github Icon",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}


