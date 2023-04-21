package com.example.firebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var loginEmailInput : EditText
    private lateinit var loginPasswordInput : EditText
    private lateinit var loginButton : Button
    private lateinit var forgotPasswordButton : TextView
    private lateinit var notRegisteredButton : TextView

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        listeners()
    }

    private fun listeners() {
        loginButton.setOnClickListener {
            val email = loginEmailInput.text.toString()
            val password = loginPasswordInput.text.toString()

            if (email.isEmpty() ) {
                Toast.makeText(this, "Email field is empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Password field is empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                if (it.isSuccessful){
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        forgotPasswordButton.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
            finish()
        }

        notRegisteredButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun init(){
        loginEmailInput = findViewById(R.id.loginEmailInput)
        loginPasswordInput = findViewById(R.id.loginPasswordInput)
        loginButton = findViewById(R.id.loginButton)
        forgotPasswordButton = findViewById(R.id.ForgotPasswordButton)
        notRegisteredButton = findViewById(R.id.notRegisteredButton)
    }
}