package com.omeryildizce.simpleintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.omeryildizce.simpleintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        println("on create")

    }

    override fun onStart() {
        super.onStart()
        println("on start")
    }

    override fun onResume() {
        super.onResume()
        println("on resume")
    }

    override fun onPause() {
        super.onPause()
        println("on pause")
    }

    override fun onStop() {
        super.onStop()
        println("on stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("on destroy")
    }


    fun next(view: View) {
        val intent = Intent(applicationContext, NextActivity::class.java)
        intent.putExtra("username", binding.username.text.toString())
        intent.putExtra("name", binding.name.text.toString())
        startActivity(intent)
    }
}