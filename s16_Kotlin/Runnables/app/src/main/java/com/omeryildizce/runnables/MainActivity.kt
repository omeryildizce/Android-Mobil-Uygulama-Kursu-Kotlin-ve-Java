package com.omeryildizce.runnables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.omeryildizce.runnables.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var number = 0
    var runnable: Runnable = Runnable { }
    var handler: Handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.start.setOnClickListener {
            number = 0
            runnable = object : Runnable {
                override fun run() {
                    number++
                    binding.time.setText(String.format("%02d:%02d:%02d", (number /3600) % 99, (number /60) % 60 , number % 60))

                    handler.postDelayed(this, 1000)
                }
            }
            handler.post(runnable)
        }
        binding.stop.setOnClickListener {
            handler.removeCallbacks(runnable)
            Toast.makeText(this,binding.time.text.toString(), Toast.LENGTH_LONG).show()
            number = 0
            binding.time.setText("00:00:00")
        }

    }
}