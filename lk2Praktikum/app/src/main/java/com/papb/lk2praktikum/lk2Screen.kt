package com.papb.lk2praktikum

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.papb.lk2praktikum.ui.theme.Lk2PraktikumTheme

@Composable
fun Tampilan(){

    var teks by remember{
        mutableStateOf("")
    }

    var inputTeks by remember{
        mutableStateOf("")
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.satu), contentDescription = "Gambar", modifier = Modifier.size(300.dp))

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Masukkan text anda !!!", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(19.dp))

        OutlinedTextField(value = inputTeks, onValueChange = {inputTeks = it}, textStyle = TextStyle(color = Color.Magenta), label = {
            Text(text = "Text Bebas Anda")
        })


        Spacer(modifier = Modifier.height(18.dp))

        Button(onClick = {teks = inputTeks}) {
            Text(text = "Submit", fontSize = 20.sp, fontFamily = FontFamily.SansSerif)
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(text = teks)
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    Lk2PraktikumTheme {
        Tampilan()
    }
}