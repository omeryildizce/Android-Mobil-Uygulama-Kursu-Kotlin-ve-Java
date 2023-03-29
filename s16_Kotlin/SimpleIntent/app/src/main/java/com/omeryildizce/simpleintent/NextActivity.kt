package com.omeryildizce.simpleintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omeryildizce.simpleintent.databinding.ActivityNextBinding

class NextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNextBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNextBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = intent
        val username = intent.getStringExtra("username")
        val name = intent.getStringExtra("name")

        binding.usernameText.setText(username)
        binding.nameText.setText(name)


    }
}