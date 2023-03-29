package com.omeryildizce.kotlinlearning.kotlin

fun main(args: Array<String>) {
    val myString: String? = null
    var myAge: Int? = null

    println(myString)
    println(myAge)
    // 1 Null safety
    if (myAge != null) {
        println(myAge * 10)
    }else{
        println(myAge)
    }
    // 2 Safe call
    println(myAge?.compareTo(2))

    // 3 elvis
    val myResult = myAge?.compareTo(2) ?: -100
    println(myResult)


}