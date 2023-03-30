package com.omeryildizce.oop

class Dog : Animal() {
    override fun sing(){
        println("dog class")
    }
    fun test(){
        super.sing()
    }
}