package com.papb.lk2praktikum

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.papb.lk2praktikum.ui.theme.Lk2PraktikumTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tampilan(onLoginClicked: (String, String) -> Unit){

    var inputEmail by remember{
        mutableStateOf("")
    }

    var inputPassword by remember{
        mutableStateOf("")
    }

    val isFormFilled = inputEmail.isNotEmpty() && inputPassword.isNotEmpty()
    val context = LocalContext.current

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.satu), contentDescription = "Gambar", modifier = Modifier.size(300.dp))

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Login Page", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(19.dp))

        OutlinedTextField(value = inputEmail, onValueChange = {inputEmail = it}, textStyle = TextStyle(color = Color.Magenta), label = {
            Row(horizontalArrangement = Arrangement.Center) {
                Image(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "icon nama",
                    modifier = Modifier.size(25.dp)
                )
                Text(text = "Email")
            }
        })
//        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        var passwordVisible by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = inputPassword,
            onValueChange = { inputPassword = it },
            textStyle = TextStyle(color = Color.Magenta),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = {
                Row(horizontalArrangement = Arrangement.Center) {
                    Image(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "icon kunci",
                        modifier = Modifier.size(25.dp)
                    )
                    Text(text = "Password")
                }
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Info else Icons.Filled.Warning,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        )



        Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = {onLoginClicked(inputEmail, inputPassword)},
            enabled = isFormFilled,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFormFilled) Color.Blue else Color.DarkGray
            )
        ) {
            Text(text = "Login", fontSize = 20.sp, fontFamily = FontFamily.SansSerif, color = if (isFormFilled) Color.White else Color.DarkGray)
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(text = "Welcome To PAPB 2024", color = Color.Cyan)
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewTampilan() {
    Lk2PraktikumTheme {
        Tampilan(onLoginClicked = { _, _ -> /* Do nothing for preview */ })
    }
}
