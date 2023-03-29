package com.omeryildizce.kotlinlearning.kotlin

import kotlin.concurrent.thread

fun main(args: Array<String>) {

    for (i in 1.until(5)){
         test()

    }
    mySum(10,20)
    val sum = mySum(10,20,30)
    println(sum)

    val result1 = sum(1, 2, 3, 4, 5)
    val result2 = sum(10, 20)
    val result3 = sum(4, 6, 8, 10, 12, 14, 16)

    println("Result 1: $result1")
    println("Result 2: $result2")
    println("Result 3: $result3")
}

fun mySum(num1: Int, num2: Int):Unit {
    println( num1 + num2)
}
fun mySum(num1: Int, num2: Int , num3:Int): Int {
    return ( num1 + num2 + num3)
}

fun sum(vararg numbers: Int): Int {
    var total = 0
    for (number in numbers) {
        total += number
    }
    return total
}
fun test() {
    Thread.sleep(100)
    println("${::test.name}")
}
