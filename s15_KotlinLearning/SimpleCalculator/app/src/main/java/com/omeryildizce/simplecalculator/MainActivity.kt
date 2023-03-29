package com.omeryildizce.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.omeryildizce.simplecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var number1 = 0
    private var number2 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



    }

    private fun initalize( ) {
        number1 = binding.editTextNumber1.text.toString().toIntOrNull() ?: 0
        number2 = binding.editTextNumber2.text.toString().toIntOrNull() ?: 0

    }
    private fun writeResult(result:Int){
        binding.textViewResult.setText("Result: $result")
    }

    fun mySum(view: View) {
        initalize()
        writeResult(number1 + number2)

    }
    fun mySub(view: View) {
        initalize()
        writeResult( number1 - number2)
    }
    fun myMultiply(view: View) {
        initalize()
        writeResult( number1 * number2)
    }
    fun myDvide(view: View) {
        initalize()
        if (number2!=0)
            writeResult( (number1 / number2).toInt())
        else Toast.makeText(this, "Number 2 cannot be 0 or null!", Toast.LENGTH_SHORT).show()
    }
}