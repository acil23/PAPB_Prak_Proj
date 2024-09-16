package com.papb.restarurantsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha
import com.papb.restarurantsapp.ui.theme.RestarurantsAppTheme

@Composable
fun RestaurantScreen() {
    // Membuat background gradien
    val gradientBackground = Brush.linearGradient(
        colors = listOf(
            Color(0xFF42A5F5),
            Color(0xFF4CAF50)
        )
    )

    val viewModel: RestaurantsViewModel = viewModel()

    Box(
        modifier = Modifier.fillMaxSize() // Mengisi seluruh layar
    ) {
        // Gambar background
        Image(
            painter = painterResource(id = R.drawable.bg1j),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Mengisi dan memotong gambar jika diperlukan
        )

        Column(
            modifier = Modifier
//                .background(gradientBackground)
                .fillMaxSize() // Mengisi seluruh layar
                .padding(16.dp) // Menambahkan padding untuk jarak dari tepi layar
        ) {
            // Menampilkan NamaApp di bagian atas, tidak ikut scroll
            NamaApp()

            // Daftar restoran dengan scroll
            LazyColumn(
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 8.dp
                )
            ) {
                items(viewModel.getRestaurants()) { restaurant ->
                    RestaurantItem(restaurant)
                }
            }
        }
    }
}

@Composable
fun NamaApp() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth() // Mengisi seluruh lebar layar
    ) {
        // Gambar pertama dengan ukuran yang disamakan
        Image(
            painter = painterResource(id = R.drawable.restoran2),
            contentDescription = "Gambar restoran",
            modifier = Modifier
                .size(48.dp) // Menetapkan ukuran tetap untuk gambar
                .clip(MaterialTheme.shapes.medium), // Memotong gambar sesuai batas
            contentScale = ContentScale.Crop // Menyesuaikan skala gambar
        )

        Spacer(modifier = Modifier.width(16.dp)) // Menambahkan jarak antara gambar dan teks

        // Teks tengah
        Text(
            text = "Restaurants",
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .weight(1f), // Membuat teks mengambil proporsi yang sama dalam Row
            style = MaterialTheme.typography.headlineMedium.copy(Color(0xF2245C43)),
            fontSize = 24.sp, fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(16.dp)) // Menambahkan jarak antara teks dan gambar berikutnya

        // Gambar kedua dengan ukuran yang disamakan
        Image(
            painter = painterResource(id = R.drawable.food2),
            contentDescription = "Gambar makanan",
            modifier = Modifier
                .size(48.dp) // Menetapkan ukuran tetap untuk gambar
                .clip(MaterialTheme.shapes.medium), // Memotong gambar sesuai batas
            contentScale = ContentScale.Crop // Menyesuaikan skala gambar
        )
    }
}

@Composable
fun RestaurantItem(item: Restaurant) {
    val gradient = Brush.linearGradient(colors = listOf(Color(0xFF1c92d2), Color(0xFFf2fcfe))
    )
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(verticalAlignment =
        Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
                .background(gradient)) {
            RestaurantIcon(
                Icons.Filled.Place,
                Modifier.weight(0.15f))
            RestaurantDetails(
                item.title,
                item.description,
                Modifier.weight(0.85f))
                FavoriteIcon(Modifier.weight(0.15f))
        }
    }
}

@Composable
private fun FavoriteIcon(modifier: Modifier) {
    val favoriteState = remember {
        mutableStateOf(false) }
    val icon = if (favoriteState.value)
        Icons.Filled.Favorite
    else
        Icons.Filled.FavoriteBorder

    Image(
        imageVector = icon,
        contentDescription = "Favorite restaurant icon",
        modifier = modifier
            .padding(8.dp)
            .clickable { favoriteState.value =
                !favoriteState.value
            }
    )
}

@Composable
private fun RestaurantIcon(icon: ImageVector, modifier: Modifier) {
    Image(
        imageVector = icon,
        contentDescription = "Restaurant icon",
        modifier = modifier.padding(8.dp)
    )
}

@Composable
private fun RestaurantDetails(title: String, description: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            fontWeight = FontWeight.W400
        )
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                fontWeight = FontWeight.W500
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RestarurantsAppTheme {
        RestaurantScreen()
    }
}