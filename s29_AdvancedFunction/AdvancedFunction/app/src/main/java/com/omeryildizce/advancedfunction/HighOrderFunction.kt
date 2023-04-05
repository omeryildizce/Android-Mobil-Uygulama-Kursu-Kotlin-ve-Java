package com.omeryildizce.advancedfunction

fun main(args: Array<String>) {
    val number = listOf<Int>(1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1,10)

    // filter
    val smalNumberSix = number.filter { num -> num < 6 }
    println(smalNumberSix.toString())

    // map
    val square = number.map { it * it }
    println(square.toString())

    val filterAndMap = number.filter { it<6 }.map { it * it }.toSortedSet()
    println(filterAndMap.toString())


    val musicans = listOf<Musician>(
        Musician("James", 45, "Piano"),
        Musician("Lars", 50, "Drum"),
        Musician("Kirk", 54, "Guitar")
    )

    val map = musicans.filter { it.age > 46 }.map { it.instrument }
    println(map.toString())
}

data class Musician(val name:String, val age:Int, val instrument:String)