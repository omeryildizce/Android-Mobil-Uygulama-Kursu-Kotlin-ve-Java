package com.omeryildizce.oop

class SuperMusicians(name: String?, instrument: String?, age: Int?) : Musician(name, instrument,
    age,) {
    fun sing(){
        println("sing")
    }
}