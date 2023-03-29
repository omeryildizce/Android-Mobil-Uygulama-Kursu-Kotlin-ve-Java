package com.omeryildizce.simpson

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.omeryildizce.simpson.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var name: String
    private lateinit var job: String
    private var age: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }

    fun makeSimpson(view: View) {
        name = binding.editTextSimpsonName.text.toString()
        job = binding.editTextTextSimpsonJob.text.toString()
        age = binding.editTextSimpsonAge.text.toString().toInt()
        val simpson = Simpsons(name, job, age)
        simpson.changeHairColor()
        binding.textViewSimpsonInformation.setText(simpson.toString())


    }
}