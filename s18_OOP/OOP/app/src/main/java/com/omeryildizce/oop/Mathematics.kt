package com.omeryildizce.oop

class Mathematics {
    fun sum(): Int{
        return 0
    }
    fun sum(x:Int, y:Int):Int{
        return x + y
    }
    fun sum(x:Int, y:Int, z:Int):Int{
        return x + y + z
    }
    fun sum(vararg x:Int) :Int{
        var sum = 0
        for (i in x){
            sum+=i
        }
        return sum
    }
}