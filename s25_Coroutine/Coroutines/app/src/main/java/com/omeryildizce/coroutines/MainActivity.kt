package com.omeryildizce.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        GlobalScope.launch {
            repeat(100_000) {
                launch {
                    println("android")
                }
            }
        }
            */
        // Scope
        // Global Scope, runBlocking ,coroutineScope
        // run blockig
        /*
        println("run blocking start")
        runBlocking {  launch {
            delay(2000)
            println("run blocking")
        } }
        println("run blocking end")
        */

        /*
        // Global scope
        println("global scope start")
        GlobalScope.launch {
            delay(5000)
            println("global scope")
        }
        println("global scope end")

         */
        /*
        // Context
        // CoroutineScope
        println("CoroutineScope start")
        CoroutineScope(Dispatchers.Default).launch {
            delay(5000)
            println("CoroutineScope")
        }
        println("CoroutineScope end")

         */

        runBlocking {
            launch(Dispatchers.Main) {
                println("Main Thread: ${Thread.currentThread().name}")
            }
            launch(Dispatchers.IO) {
                println("IO Thread: ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Default) {
                println("Default Thread: ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Unconfined) {
                println("Unconfined: ${Thread.currentThread().name}")
            }
        }
    }
}