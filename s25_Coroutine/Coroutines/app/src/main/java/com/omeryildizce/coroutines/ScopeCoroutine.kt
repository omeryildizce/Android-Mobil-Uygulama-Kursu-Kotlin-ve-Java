package com.omeryildizce.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    runBlocking {
            launch {
                delay(5_000)
                println("run")
            }
            coroutineScope {
                launch {
                    delay(3000)
                    println("CoroutineScope")
                }
            }
    }
}