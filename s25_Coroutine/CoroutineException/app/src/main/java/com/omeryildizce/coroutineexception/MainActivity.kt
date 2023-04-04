package com.omeryildizce.coroutineexception

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.omeryildizce.coroutineexception.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("exception: ${throwable.localizedMessage}")

        }

        lifecycleScope.launch(handler) {
            launch {

                throw Exception("error")
            }
            launch {
                delay(2000)
                println("this is executed")
            }
        }



        lifecycleScope.launch(handler) {
            supervisorScope {

                launch {

                    throw Exception("error")
                }
                launch {
                    delay(2000)
                    println("this is executed")
                }
            }
        }
    }
}