package com.example.banksampahkelompok4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var editemaillogin : EditText
    private lateinit var passwordlogin : EditText
    private lateinit var buttonlogin :   ImageView
    private lateinit var signin : TextView
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        editemaillogin = findViewById(R.id.emaillogin)
        passwordlogin = findViewById(R.id.passwordlogin)
        buttonlogin = findViewById(R.id.loginbtn)
        signin = findViewById(R.id.signinlogin)
        firebaseAuth = FirebaseAuth.getInstance()

        buttonlogin.setOnClickListener {
            val email = editemaillogin.text.toString()
            val password = passwordlogin.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                return@setOnClickListener
            }else{
                prosesLogin(email,password)
            }
        }

    }
    fun prosesLogin (email: String,password: String){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            val toMain = Intent(this,MainActivity::class.java)
            startActivity(toMain)
        }.addOnFailureListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}