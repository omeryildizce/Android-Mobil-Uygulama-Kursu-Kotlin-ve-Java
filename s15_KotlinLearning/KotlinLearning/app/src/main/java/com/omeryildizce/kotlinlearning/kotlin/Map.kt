package com.omeryildizce.kotlinlearning.kotlin

fun main(args: Array<String>) {
    val fruitArray = HashMap<String, Int>()
    fruitArray.put("apple", 100)
    fruitArray.put("banana", 150)

    val extraValue = hashMapOf<String, Int>("orange" to 120, "mango" to 100)

    val fruitCalories = hashMapOf(
        "apple" to 52,
        "banana" to 89,
        "orange" to 47,
        "grapefruit" to 41,
        "strawberry" to 33,
        "blueberry" to 57,
        "raspberry" to 52,
        "blackberry" to 43,
        "cherry" to 50,
        "peach" to 39,
        "plum" to 46,
        "pear" to 57,
        "kiwi" to 61,
        "mango" to 99,
        "pineapple" to 82,
        "watermelon" to 30,
        "grape" to 69,
        "melon" to 64,
        "pomegranate" to 83,
        "apricot" to 48
    )

    println(fruitCalories.toString())
    println("${fruitCalories["apple"]}")
}