package com.omeryildizce.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.omeryildizce.viewbinding.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root;
        setContentView(view)

    }

    fun degistir(view: View) {
        var randInt = Random.nextInt(2)
        if (randInt == 0){
            binding.textViewName.setText( "YILDIZ Ömer")

        }else{
            binding.textViewName.setText( "Ömer YILDIZ")

        }
    }
}