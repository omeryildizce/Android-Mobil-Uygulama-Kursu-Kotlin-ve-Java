package com.omeryildizce.kotlinlearning.kotlin


fun main(args: Array<String>) {
    val array = Array(30) { i -> (i * 3) + 3 }

    for (number in array) {
        println(number)
    }

    val array1 = arrayOf(1..25 step 2)
    println(array1[0])

    var a = 0
    while (a<10){
        a++
        println(a)
    }
}