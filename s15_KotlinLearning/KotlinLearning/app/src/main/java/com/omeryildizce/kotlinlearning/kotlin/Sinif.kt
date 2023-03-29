package com.omeryildizce.kotlinlearning.kotlin

fun main(args: Array<String>) {
    var homer = Simpson(50, "homer", "nuclear")

    println(homer.name)
    println(homer.age)
    println(homer.job)
    println(homer.toString())

    // homer.hairColor = "yellow"
    homer.changeHairColor()
    println(homer.hairColor)

}
