package com.omeryildizce.oop

// Constructor vs init
data class User(val name: String?, val age: Int?): People() {

    init {
        println("init")
    }

}