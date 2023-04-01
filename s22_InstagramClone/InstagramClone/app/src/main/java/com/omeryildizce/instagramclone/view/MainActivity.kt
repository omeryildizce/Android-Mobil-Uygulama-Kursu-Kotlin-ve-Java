package com.omeryildizce.instagramclone.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.omeryildizce.instagramclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        resetPassword()
        checkCurrentUserAndStartActivity()
        logIn()
        signUp()
    }

    private fun resetPassword() {
        binding.tvForgotPassword.setOnClickListener {
        val intent = Intent(this, ResetPasswordActivity::class.java)
        startActivity(intent)
        finish()
        }
    }

    fun checkIfEmailVerifiedAndLogin() {
        val user = auth.currentUser
        if (user?.isEmailVerified == true) {
            // Kullanıcı doğrulama işlemi tamamlamış, giriş yapılabilir
            // Giriş yapma işlemleri burada gerçekleştirilebilir
            val intent = Intent(this, FeedActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            // Kullanıcı doğrulama işlemi tamamlanmamış, hata mesajı gösterilebilir
            Toast.makeText(this, "Please verify your email address.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkCurrentUserAndStartActivity() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            checkIfEmailVerifiedAndLogin()
        }
    }

    private fun logIn() {
        binding.btnLogin.setOnClickListener {

            val email = binding.etLoginEmail.text.toString()
            val password = binding.etLoginPassword.text.toString()

            if (email.equals("") || password.equals("")) {
                Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    checkIfEmailVerifiedAndLogin()

                }.addOnFailureListener {
                    Toast.makeText(
                        this,
                        it.localizedMessage ,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun signUp() {
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}