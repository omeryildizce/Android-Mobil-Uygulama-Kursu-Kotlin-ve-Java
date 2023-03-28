package com.omeryildizce.kotlinlearning.kotlin

fun main(args: Array<String>) {
    // Arrays
    arrays()
}

fun arrays() {
    val  myArray = arrayOf("Ali", "Ömer", "Ayşe", "Veli")
    println(myArray[1])
    println(myArray.forEach { println(it) })
    myArray.set(0,"Van")

    val numberArray = arrayOf(1,2,3,4,5)
    println(numberArray[3])

    val myNewArray = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
    val mixedArray = arrayOf("Ömer", 1, true, 2.0, 3.0f, 'a')
    mixedArray.forEach { print("" + it +" ") }


}
