package com.omeryildizce.coroutines

import kotlinx.coroutines.*

fun main(args: Array<String>) {

    runBlocking {
        delay(5000)
        println("run block")
        myFunction()
    }
}

suspend fun myFunction() {
    coroutineScope {
        delay(4474)
        println("suspend func")
    }
}