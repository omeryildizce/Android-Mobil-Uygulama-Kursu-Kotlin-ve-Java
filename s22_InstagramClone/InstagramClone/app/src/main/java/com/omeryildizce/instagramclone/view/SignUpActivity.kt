package com.omeryildizce.instagramclone.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.omeryildizce.instagramclone.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        createNewUser()
        gotoLogin()
    }

    private fun gotoLogin() {
        binding.tvlogin.setOnClickListener {
            val intent = Intent(this@SignUpActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun createNewUser() {
        binding.btnSignUp.setOnClickListener {

            val username = binding.etSignUpUsername.text.toString()
            val email = binding.etSignUpemail.text.toString()
            val password = binding.etSignUppassword.text.toString()

            if (email.equals("") || password.equals("") || username.equals("")) {
                Toast.makeText(this, "Please fill in the blank spaces. ", Toast.LENGTH_SHORT).show()
            } else {
                auth.fetchSignInMethodsForEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val result = task.result?.signInMethods ?: emptyList<String>()
                            if (result.isNotEmpty()) {
                                // Email already registered, show an alert dialog
                                showEmailAlreadyRegisteredDialog()
                            } else {
                                auth.createUserWithEmailAndPassword(email, password)
                                    .addOnSuccessListener {
                                        updateUsername(username)
                                        sendEmailVerification()
                                        gotoLoginScreen()
                                    }.addOnFailureListener {
                                    Toast.makeText(
                                        this@SignUpActivity,
                                        it.localizedMessage,
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
                        } else {
                            Toast.makeText(this, "An error occurred!", Toast.LENGTH_SHORT).show()

                        }
                    }


            }

        }
    }

    private fun updateUsername(username: String) {
        val user = auth.currentUser
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(username)
            .build()
        user?.updateProfile(profileUpdates)
            ?.addOnCompleteListener { updateTask ->
                if (updateTask.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Username updated successfully.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        "Error occurred",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }
    }

    private fun showEmailAlreadyRegisteredDialog() {
        AlertDialog.Builder(this)
            .setTitle("Email already registered")
            .setMessage("The email address you entered is already registered.")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun gotoLoginScreen() {
        val intent =
            Intent(this@SignUpActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun sendEmailVerification() {
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Email sent
                    Toast.makeText(this, "Verification email sent.", Toast.LENGTH_SHORT).show()
                } else {
                    // Error occurred
                    Toast.makeText(this, "Failed to send verification email.", Toast.LENGTH_SHORT).show()
                }
            }

    }
}