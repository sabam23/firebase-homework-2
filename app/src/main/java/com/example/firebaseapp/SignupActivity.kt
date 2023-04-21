package com.example.firebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.FirebaseAuthKtxRegistrar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignupActivity : AppCompatActivity() {

    private lateinit var signupEmailInput : EditText
    private lateinit var signupPasswordInput : EditText
    private lateinit var signupButton : Button
    private lateinit var alreadyRegisteredButton : TextView

    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        init()
        listeners()
    }

    private fun listeners() {
        signupButton.setOnClickListener {
            val email = signupEmailInput.text.toString()
            val password = signupPasswordInput.text.toString()

            if (email.isEmpty() || password.isEmpty() || email.contains(" ") ||
                password.length < 8){
                for ( a in password){
                    if (a.isUpperCase()){
                        return@setOnClickListener
                    }
                }
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        alreadyRegisteredButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun init(){
        signupEmailInput = findViewById(R.id.signupEmailInput)
        signupPasswordInput = findViewById(R.id.signupPasswordInput)
        signupButton = findViewById(R.id.signupButton)
        alreadyRegisteredButton = findViewById(R.id.alreadyRegisteredButton)
    }
}