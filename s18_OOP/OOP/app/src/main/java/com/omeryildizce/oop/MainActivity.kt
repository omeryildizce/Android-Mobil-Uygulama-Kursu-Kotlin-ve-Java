package com.omeryildizce.oop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omeryildizce.oop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Constructor
        constructorEx()
        // encapsulation
        encapsulationEx()
        // inheritance
        inheritanceEx()
        // polymorphisim
        polymorphisimEx()
        // abstract & interface
        abstractInterface()
        // lambda
        lambdaEx()
    }

    private fun lambdaEx() {
        printString("mytest")
        val testString = { myString: String ->
            println(myString)
        }
        testString("my lambda")

        val multiply = {a:Int, b:Int ->  a * b}
        println(multiply(15,25))
        val multiply2 : (Int, Int) -> Int = {a, b -> a * b}
        println(multiply2(15,14))

        // async
        downloadMusicians("", {
            println("completed")
        })
    }
    fun downloadMusicians(url:String , completion : () -> Unit){
        // url -> download
        completion()
    }
    fun printString(myString: String) {
        println(myString)
    }

    private fun abstractInterface() {
        var myUser = User("James", 56)
        println(myUser.information())

        var myPiano = Piano()
        myPiano.brand = "Yamaha"
        myPiano.digital = false
        println(myPiano.roomName)
        myPiano.info()
    }

    private fun polymorphisimEx() {
        //static
        var mathematics = Mathematics()
        println(mathematics.sum())
        println(mathematics.sum(1))
        println(mathematics.sum(1, 2, 3, 4, 5, 6))
        println(mathematics.sum(1, 2))
        println(mathematics.sum(1, 2, 3))
        // dynamic
        val animal = Animal()
        animal.sing()

        val barley = Dog()
        barley.test()
        barley.sing()

    }

    private fun inheritanceEx() {
        var lars = SuperMusicians("Lars", "Bateri", 65)
        println(lars.name)
        println(lars.returnBandName("123"))
        lars.sing()
    }

    private fun encapsulationEx() {
        var james = Musician("James", "Gitar", 55)
        println(james.age)
        println(james.returnBandName("1234"))
        println(james.returnBandName("123"))
    }

    private fun constructorEx() {
        var myUser = User("James", 56)
        println(myUser.age)
        println(myUser.name)
        println(myUser.toString())
    }


}