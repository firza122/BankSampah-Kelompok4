package com.example.banksampahkelompok4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    private lateinit var editEmail:EditText
    private lateinit var editUsername:EditText
    private lateinit var editPassword:EditText
    private lateinit var btnRegister: ImageView
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var editLogin: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editEmail=findViewById(R.id.email)
        editUsername=findViewById(R.id.username)
        editPassword=findViewById(R.id.password)
        btnRegister = findViewById(R.id.signup)
        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        editLogin = findViewById(R.id.signinlogin)

        btnRegister.setOnClickListener {
            val email = editEmail.text.toString()
            val username = editUsername.text.toString()
            val password = editPassword.text.toString()

            if (email.isEmpty() || username.isEmpty() || password.isEmpty()){
                return@setOnClickListener
            }else{
                prosesRegister(email,password,username)
            }
        }
        editLogin.setOnClickListener {
            val toLogin = Intent(this,RegisterActivity::class.java)
            startActivity(toLogin)
        }
    }
    fun prosesRegister(email:String,password: String,username: String){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            val user = hashMapOf(
                "email" to email,
                "username" to username
            )
            firestore.collection("user").add(user).addOnSuccessListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Gagal Login", Toast.LENGTH_SHORT).show()
        }
    }
}