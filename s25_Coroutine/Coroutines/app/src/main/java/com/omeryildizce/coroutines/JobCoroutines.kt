package com.omeryildizce.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    // job
    runBlocking {
        val myJob = launch {
            delay(2000)
            println("job")
            val secondJob = launch {
                println("job2")
            }
        }

        myJob.invokeOnCompletion {
            println("my job end")
        }
        myJob.cancel()
    }


}