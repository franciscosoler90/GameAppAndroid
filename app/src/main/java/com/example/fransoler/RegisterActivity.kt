package com.example.fransoler

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import entidades.UserData
import interfaces.LoginInterface
import ui.components.login.RegisterForm
import ui.theme.AppTheme

class RegisterActivity : ComponentActivity(), LoginInterface {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterForm(this@RegisterActivity)
                }
            }
        }
    }

    override fun loginActivity() {
        val intent = Intent(baseContext, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun createAccount(userData: UserData) {
        auth.createUserWithEmailAndPassword(userData.email, userData.pwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    updateName(userData)
                } else {
                    Toast.makeText(baseContext, R.string.errorAuth, Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun updateName(userData: UserData) {
        val user = auth.currentUser
        val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(userData.name).build()

        user?.updateProfile(profileUpdates)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, R.string.errorUpdate, Toast.LENGTH_SHORT).show()
                }
            }

    }

    override fun registerActivity() {
        //Nada
    }

    override fun signIn(userData: UserData) {
        //Nada
    }

}