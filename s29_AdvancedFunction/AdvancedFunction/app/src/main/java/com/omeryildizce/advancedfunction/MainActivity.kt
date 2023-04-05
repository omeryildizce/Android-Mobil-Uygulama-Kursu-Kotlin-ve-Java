package com.omeryildizce.advancedfunction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private var myInt : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myInt?.let {
            val num = it + 1
        }

        val myNum = myInt?.let { it + 1 } ?: 0

        // also
        val atil = Person("Atıl", 35)
        val atlas = Person("Atlas", 45)
        val zeynep = Person("zeynep", 40)

        val people = listOf<Person>(atil, atlas, zeynep)
        people.filter { it.age > 36 }.also{ println(it.toString()) }

        // apply
        val intent = Intent()
        intent.putExtra("","")
        intent.putExtra("","")
        intent.putExtra("","")
        intent.`package` = ""
        intent.action = ""

        val intentWithApply = Intent().apply {
            putExtra("","")
            putExtra("","")
            putExtra("","")
            `package`=""
            action = ""
        }

        // with
        Person("barley", 45).apply {
            name = "ally"
        }.also { println(it.name) }

        val newBarley = Person("bar",44).apply {
            name = "ally"
        }

        println(newBarley.name)

        val  anotherBarley = with(Person("bar", 4)) {
            name = "ally"
        }
        println(anotherBarley)

        val withBarley = Person("bar", 4)
            with(withBarley){
            name = "ally"
                age = 144
        }

        println(withBarley.name )
    }
}

data class Person(var name:String, var age:Int)