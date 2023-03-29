package com.omeryildizce.storingdata

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omeryildizce.storingdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var age: Int = Int.MIN_VALUE
    private lateinit var sharedPreferences: SharedPreferences
    private var state: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Shared prefences
        sharedPreferences =
            this.getSharedPreferences("com.omeryildizce.storingdata", MODE_PRIVATE)

        state = initilatize()
        binding.save.setOnClickListener {
            age = binding.editTextAge.text.toString().toIntOrNull() ?: -27
            if (age != -27) {
                sharedPreferences.edit().putInt("age", age).apply()
                initilatize()
            }
        }
        binding.delete.setOnClickListener {
            state = initilatize()
            if (state) {
                sharedPreferences.edit().remove("age").apply()
                initilatize()
            }
        }
    }

    private fun initilatize(): Boolean {
        age = sharedPreferences.getInt("age", Int.MIN_VALUE)
        if (age == Int.MIN_VALUE) {
            binding.textViewAge.setText("Your age:")
            return false
        } else {
            binding.textViewAge.setText("Your age: $age")
            return true
        }
    }
}