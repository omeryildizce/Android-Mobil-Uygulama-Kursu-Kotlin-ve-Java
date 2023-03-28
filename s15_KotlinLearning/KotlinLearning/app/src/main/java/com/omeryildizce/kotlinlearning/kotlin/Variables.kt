package com.omeryildizce.kotlinlearning

import java.util.*

fun main() {
    // Integer
    // varInteger()

    // varDouble
    // varDouble()

    // String
    // varString()

    // boolean
    // varBoolean()

    // casting
    cast()
}

fun cast() {
    var myNumber = 5
    var myLongNumber = myNumber.toLong()

    var input = "10"
    var inputInteger = input.toInt()

    println(inputInteger * 2)


}

fun varBoolean() {
    var myBoolean = true
    myBoolean = false

    /**
     * <
     * >
     * <=
     * >=
     * ==
     * !=
     * &&
     * ||
     */

    println(3 < 5)
    println(6 < 5)
    println(3 == 3)
    println(3 != 3)



}

fun varString() {
    val myString = "Ömer"
    val name ="Ömer"
    val surname = "YILDIZ"
    val fullName: String = name + " "+ surname
    println(fullName)
    myString.lowercase()
    myString.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    println(myString)
}


fun varDouble() {
    val pi = Math.PI
    println(pi)

   val myFloat = 3.14f
    println(myFloat)

    var myAge:Double
    myAge = 23.0
    println(myAge / 2)

}

private fun varInteger() {
    var x = 5
    var y = 4
    println(x * y)

    var age = 35
    println(age / 7 * 5)
    val result = age / 7 * 5
    println(result)
    age = 36
    println(age)

    val a = 5
    val myInteger: Int
    myInteger = 10

    var myLong : Long = 10
}
