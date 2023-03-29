package com.omeryildizce.kotlinlearning.kotlin

import java.util.*

class Simpson(var age: Int, var name: String, var job: String) {
    var hairColor = ""
    fun changeHairColor(){
        this.hairColor = "Yellow"
    }



    override fun toString(): String {
        return "Simpson Age:$age, Name:${
            name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
        }, Job:${job.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}"
    }

}