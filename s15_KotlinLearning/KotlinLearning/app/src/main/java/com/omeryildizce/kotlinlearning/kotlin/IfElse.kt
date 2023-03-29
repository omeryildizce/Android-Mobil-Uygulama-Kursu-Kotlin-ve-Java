package com.omeryildizce.kotlinlearning.kotlin

fun main(args: Array<String>) {
    val  myNumberAge = 32
    if (myNumberAge < 30){
        println("<30")
    }else if (myNumberAge >= 30 && myNumberAge < 40){
        println("30-40")
    }else{
        println(">50")

    }
}