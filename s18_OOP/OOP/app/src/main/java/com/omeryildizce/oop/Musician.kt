package com.omeryildizce.oop

open class Musician(name: String?, instrument: String?, age: Int?) {
    // encapsulation
    var name: String? = null
        private set
        get
    var instrument: String? = null

    var age: Int? = null
        get
        private set

    private val bandName: String = "Metalica"
    fun returnBandName(password: String): String {
        if (password == "123") {
            return bandName
        }
        else
             return "Wrong password!"
    }
}