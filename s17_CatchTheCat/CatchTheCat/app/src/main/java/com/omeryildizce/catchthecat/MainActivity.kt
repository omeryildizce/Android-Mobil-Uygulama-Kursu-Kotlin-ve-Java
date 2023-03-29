package com.omeryildizce.catchthecat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.omeryildizce.catchthecat.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private var score = 0
    private lateinit var binding: ActivityMainBinding
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable { }
    var handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Image Array
        initialize()
        hideImages()
        showImages()


        object : CountDownTimer(15_000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.time.text = String.format("Time: 00:%02d", millisUntilFinished / 1000)
            }

            override fun onFinish() {
                binding.time.text = String.format("Time: 00:00")
                handler.removeCallbacks(runnable)
                hideImages()
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Your Score $score\nRestart The Game?")
                alert.setPositiveButton("yes") { dialog, which ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("no") { dialog, which ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_LONG).show()
                }

                alert.show()
            }
        }.start()
    }

    private fun showImages() {
        runnable = object : Runnable {
            override fun run() {
                hideImages()
                val random = Random()
                val randomNumber = random.nextInt(30)
                imageArray[randomNumber].visibility = View.VISIBLE
                handler.postDelayed(runnable,750)

            }
        }

        handler.post(runnable)

    }
    private fun hideImages(){
        for (image in imageArray) {
            image.visibility = View.INVISIBLE
        }
    }
    private fun initialize() {
        imageArray.add(binding.cat01)
        imageArray.add(binding.cat02)
        imageArray.add(binding.cat03)
        imageArray.add(binding.cat04)
        imageArray.add(binding.cat05)
        imageArray.add(binding.cat06)
        imageArray.add(binding.cat07)
        imageArray.add(binding.cat08)
        imageArray.add(binding.cat09)
        imageArray.add(binding.cat10)

        imageArray.add(binding.cat11)
        imageArray.add(binding.cat12)
        imageArray.add(binding.cat13)
        imageArray.add(binding.cat14)
        imageArray.add(binding.cat15)
        imageArray.add(binding.cat16)
        imageArray.add(binding.cat17)
        imageArray.add(binding.cat18)
        imageArray.add(binding.cat19)
        imageArray.add(binding.cat20)

        imageArray.add(binding.cat21)
        imageArray.add(binding.cat22)
        imageArray.add(binding.cat23)
        imageArray.add(binding.cat24)
        imageArray.add(binding.cat25)
        imageArray.add(binding.cat26)
        imageArray.add(binding.cat27)
        imageArray.add(binding.cat28)
        imageArray.add(binding.cat29)
        imageArray.add(binding.cat30)
    }

    fun increaseScore(view: View) {
        score++
        binding.score.text = String.format("Score: %02d", score)
    }

}