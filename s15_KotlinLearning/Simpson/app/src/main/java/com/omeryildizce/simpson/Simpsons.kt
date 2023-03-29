package com.omeryildizce.simpson

class Simpsons(var name:String, var job:String, var age:Int) {
    var hairColor = ""
    fun changeHairColor(){
        hairColor = "yellow"
    }

    override fun toString(): String {
        return "Simpson name: '$name'\njob: '$job'\nage=$age,\nhairColor: '$hairColor')"
    }


}