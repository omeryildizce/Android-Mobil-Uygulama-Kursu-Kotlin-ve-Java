package com.omeryildizce.storingdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omeryildizce.storingdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var age:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // S

        binding.save.setOnClickListener {
            age = binding.editTextAge.text.toString().toIntOrNull() ?: 0
            binding.textViewAge.setText("Your age: $age")
        }
        binding.delete.setOnClickListener {

        }
    }
}