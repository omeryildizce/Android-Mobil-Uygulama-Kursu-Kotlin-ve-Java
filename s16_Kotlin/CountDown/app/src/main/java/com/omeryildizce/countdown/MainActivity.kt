package com.omeryildizce.countdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.omeryildizce.countdown.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        object : CountDownTimer(59_000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                println(millisUntilFinished)
                binding.counter.setText(String.format("%02d:%02d:%02d", 0, 0, (millisUntilFinished/1000).toInt()))
            }

            override fun onFinish() {
                binding.counter.setText("Time over")

            }

        }.start()

    }
}