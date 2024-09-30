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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import entity.UserData
import interfaces.LoginInterface
import ui.components.login.LoginForm
import ui.theme.AppTheme

class LoginActivity : ComponentActivity(), LoginInterface {

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
                    LoginForm(this@LoginActivity)
                }
            }
        }
    }

    override fun signIn(userData: UserData) {
        if (userData.email.isEmpty() || userData.pwd.isEmpty()) {
            Toast.makeText(baseContext, R.string.errorFieldsEmpty, Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(userData.email, userData.pwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    task.exception?.let { exception ->
                        when (exception) {
                            is FirebaseAuthInvalidCredentialsException -> {
                                Toast.makeText(baseContext, R.string.errorInvalidCredentials, Toast.LENGTH_SHORT).show()
                            }
                            is FirebaseAuthInvalidUserException -> {
                                Toast.makeText(baseContext, R.string.errorUserNotFound, Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                Toast.makeText(baseContext, R.string.errorLogin, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
    }

    override fun registerActivity() {
        val intent = Intent(baseContext, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun createAccount(userData: UserData) {
        //Nada
    }

    override fun updateName(userData: UserData) {
        //Nada
    }

    override fun loginActivity() {
        //Nada
    }
}