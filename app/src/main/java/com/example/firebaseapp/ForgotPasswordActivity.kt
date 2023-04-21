package com.example.firebaseapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {


    private lateinit var recoveryButton : Button
    private lateinit var recoveryEmail : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpassword)

        recoveryButton = findViewById(R.id.forgotButton)
        recoveryEmail = findViewById(R.id.forgotEmailEditText)

        recoveryButton.setOnClickListener {
            val email = recoveryEmail.text.toString()
            if(email.isEmpty()) {
                Toast.makeText(this, "Please enter Email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
                if(it.isSuccessful){
                    startActivity(Intent(this, LoginActivity::class.java))
                    Toast.makeText(this, "Check Email", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}