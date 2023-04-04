package com.omeryildizce.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    repeat(10){
        print("*")
    }

    // Dispatchers
    // Dispatchers.Default -> CPU intensive
    // Dispatchers.IO -> Input / Output networking
    // Dispatchers.Main -> UI
    // Dispatchers.Unconfined -> Inherited Dispatcher

    thradEx()
}

private fun thradEx() {
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