package com.omeryildizce.advancedfunction

fun main() {
    val numList = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2, 1, 10)
    val allCheck = numList.all { it > 5 }
    println(allCheck)

    val anyCheck = numList.any { it > 5 }
    println(anyCheck)

    val totalCount = numList.count { it > 5 }
    println(totalCount)

    val findNum = numList.find { it > 5 }
    println(findNum)

    val findLastNum = numList.findLast { it > 5 }
    println(findLastNum)

    val myPredicate = { num: Int -> num > 5 }
    val allCheckWithPredicate = numList.all(myPredicate)

}