package com.omeryildizce.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main(args: Array<String>) {


    runBlocking {
        launch(Dispatchers.Default) {
            println("Context: $coroutineContext")
            withContext(Dispatchers.IO) {
                println("Context: $coroutineContext")

            }
        }
    }
}