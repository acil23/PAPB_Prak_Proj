package com.papb.lk2praktikum

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.auth.FirebaseAuth
import com.papb.lk2praktikum.ui.theme.Lk2PraktikumTheme
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        enableEdgeToEdge()
        setContent {
            Lk2PraktikumTheme {
                Tampilan(onLoginClicked = { email, password ->
                    loginWithEmail(email, password)
                })
            }
        }

        // Cek inisialisasi Firebase
//        if (FirebaseApp.getApps(this).isNotEmpty()) {
//            Log.d("FirebaseCheck", "Firebase is connected successfully")
//        } else {
//            Log.d("FirebaseCheck", "Firebase connection failed")
//        }
    }

    private fun loginWithEmail(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Login successful
                        val intent = Intent(this, ListActivity::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    } else {
                        // Login failed
                        Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Email and password must not be empty", Toast.LENGTH_SHORT).show()
        }
    }
}
