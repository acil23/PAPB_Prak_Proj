package com.papb.lk2praktikum.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.papb.lk2praktikum.GithubUserResponse
import com.papb.lk2praktikum.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@Composable
fun ProfileScreen() {
    var userProfile by remember { mutableStateOf<GithubUserResponse?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                // Ganti dengan token akses GitHub Anda
                val token = "Bearer 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"
                val response = RetrofitInstance.api.getUserProfile(token)
                userProfile = response
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    // Menampilkan UI berdasarkan data API
    userProfile?.let { profile ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color(0xFFECEFF1)), // Warna latar belakang yang lembut
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "My GitHub Profile",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333), // Warna teks
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Card untuk menampilkan informasi profil
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp) // Sudut yang lebih membulat
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Menampilkan gambar profil dari URL
                    Image(
                        painter = rememberAsyncImagePainter(profile.avatar_url),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape) // Border pada gambar profil
                            .padding(4.dp), // Padding di dalam border
                        contentScale = ContentScale.Crop
                    )

                    // Menampilkan Username
                    Text(
                        text = "@${profile.login}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    // Menampilkan Nama
                    Text(
                        text = profile.name ?: "Unknown Name",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    // Baris untuk menampilkan jumlah followers dan following
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        // Menampilkan jumlah following
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Following", fontWeight = FontWeight.Bold)
                            Text(text = profile.following.toString(), fontSize = 18.sp)
                        }

                        // Menampilkan jumlah followers
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Followers", fontWeight = FontWeight.Bold)
                            Text(text = profile.followers.toString(), fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    } ?: run {
        // Menampilkan loading spinner saat data masih null
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator() // Indikator loading
        }
    }
}