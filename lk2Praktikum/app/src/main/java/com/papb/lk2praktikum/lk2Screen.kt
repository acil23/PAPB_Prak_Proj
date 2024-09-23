package com.papb.lk2praktikum

import android.inputmethodservice.Keyboard
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.papb.lk2praktikum.ui.theme.Lk2PraktikumTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tampilan(){

    var teks by remember{
        mutableStateOf("")
    }

    var inputTeks by remember{
        mutableStateOf("")
    }

    var teks2 by remember{
        mutableStateOf("")
    }

    var inputTeks2 by remember{
        mutableStateOf("")
    }

    val isFormFilled = inputTeks.isNotEmpty() && inputTeks2.isNotEmpty()
    var context = LocalContext.current

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
            Row(horizontalArrangement = Arrangement.Center) {
                Image(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "icon nama",
                    modifier = Modifier.size(25.dp)
                )
                Text(text = "Text Bebas Anda")
            }
        })

        OutlinedTextField(keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),value = inputTeks2, onValueChange = {inputTeks2 = it}, textStyle = TextStyle(color = Color.Magenta), label = {
            Row(horizontalArrangement = Arrangement.Center) {
                Image(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "icon kunci",
                    modifier = Modifier.size(25.dp)
                )
                Text(text = "Bebas tapi cuma angka")
            }
        })


        Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = {teks = inputTeks; teks2 = inputTeks2},
            enabled = isFormFilled,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFormFilled) Color.Blue else Color.DarkGray
            )
        ) {
            Text(modifier = Modifier
                .combinedClickable(
                    onClick = {teks = inputTeks; teks2 = inputTeks2},
                    onLongClick = {
                        Toast.makeText(context, "Nama: $inputTeks\nNIM: $inputTeks2", Toast.LENGTH_SHORT).show()
                    },
                    enabled = isFormFilled
                ),
                text = "Submit", fontSize = 20.sp, fontFamily = FontFamily.SansSerif, color = if (isFormFilled) Color.White else Color.DarkGray)
        }


        Spacer(modifier = Modifier.height(18.dp))

        Text(text = teks)

        Spacer(modifier = Modifier.height(18.dp))

        Text(text = teks2)
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    Lk2PraktikumTheme {
        Tampilan()
    }
}