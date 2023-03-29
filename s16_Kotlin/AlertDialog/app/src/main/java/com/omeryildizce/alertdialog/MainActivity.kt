package com.omeryildizce.alertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.omeryildizce.alertdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Toast.makeText(applicationContext, "Welcome", Toast.LENGTH_SHORT).show()
        save()

    }

    private fun save() {
        binding.save.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Save")
            alert.setMessage("Are You Sure?")
            alert.setPositiveButton("yes"){dialog, which ->
                Toast.makeText(applicationContext, "Saved", Toast.LENGTH_SHORT).show()
            }
            alert.setNegativeButton("no"){dialog, which ->
                Toast.makeText(applicationContext, "Not Saved", Toast.LENGTH_SHORT).show()

            }
            alert.show()

        }
    }
}