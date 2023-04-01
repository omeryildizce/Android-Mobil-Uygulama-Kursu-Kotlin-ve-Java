package com.omeryildizce.instagramclone.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.omeryildizce.instagramclone.databinding.ActivityResetPasswordBinding


class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        sendPasswordResetEmail()
        binding.textViewBack.setOnClickListener {

        returnLoginActivity()

        }
    }

    private fun sendPasswordResetEmail() {
        binding.buttonReset.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            if (email.isNotEmpty()) {

                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Password reset email sent successfully
                            Toast.makeText(
                                this@ResetPasswordActivity,
                                "Password reset email sent",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            returnLoginActivity()
                        } else {
                            // There was an error sending the password reset email
                            Toast.makeText(
                                this@ResetPasswordActivity,
                                "Error sending password reset email",
                                Toast.LENGTH_SHORT
                            ).show()
                            returnLoginActivity()

                        }
                    }

            }else{
                Toast.makeText(this, "Please enter a email", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun returnLoginActivity() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


}