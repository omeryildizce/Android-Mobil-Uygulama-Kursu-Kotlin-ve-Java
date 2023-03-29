package com.omeryildizce.kotlinlearning.kotlin

fun main(args: Array<String>) {
    val myExampleArray = arrayOf(1,1,2,3,4)
    println("first element: ${myExampleArray[0]}")

    val mySet = setOf<Int>(1,1,2,3)
    println(mySet.toString())
    println(mySet.size)

    for ( value in mySet){
        print("$value")
    }
    println()
    val myString = HashSet<String>()
    myString.add("Ömer")
    myString.add("Ömer")
    println(myString.toString())

}