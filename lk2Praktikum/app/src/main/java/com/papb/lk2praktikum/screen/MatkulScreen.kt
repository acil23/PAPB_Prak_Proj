package com.papb.lk2praktikum.screen

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.papb.lk2praktikum.GithubProfileActivity
import com.papb.lk2praktikum.R
import kotlinx.coroutines.tasks.await

@Composable
fun MatkulScreen() {
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

//        // Floating Action Button (FAB) di pojok kanan bawah
//        val context = LocalContext.current
//        FloatingActionButton(
//            onClick = {
//                val intent = Intent(context, GithubProfileActivity::class.java)
//                context.startActivity(intent)
//            },
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(16.dp)
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.github),
//                contentDescription = "Github Icon",
//                modifier = Modifier.size(50.dp)
//            )
//        }
    }
}