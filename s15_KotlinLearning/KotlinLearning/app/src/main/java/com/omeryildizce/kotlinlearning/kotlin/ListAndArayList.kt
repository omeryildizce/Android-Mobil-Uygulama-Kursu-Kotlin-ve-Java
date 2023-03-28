package com.omeryildizce.kotlinlearning.kotlin

fun main(args: Array<String>) {
    val myMusician = arrayListOf<String>("a","n")
    println(myMusician.toString())
    myMusician.add("c")
    myMusician.add(0,"f")
    println(myMusician.toString())

    val myMixedArrayList = ArrayList<Any>()
    myMixedArrayList.add(10)
    myMixedArrayList.add("a")
    myMixedArrayList.addAll(arrayListOf(10,15,true))
    println(myMixedArrayList.toString())


}