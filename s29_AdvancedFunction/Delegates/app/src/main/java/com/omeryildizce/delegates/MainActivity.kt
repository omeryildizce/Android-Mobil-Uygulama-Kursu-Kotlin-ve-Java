package com.omeryildizce.delegates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.*
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MainActivity : AppCompatActivity() , LifecycleLogger by LifecycleLoggerImplementation(){
    // property delegate
    private  val myVariable by lazy {
        println("hello  this is a layz implementation")
        10  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerLifecycleOwner(this)
        println(myVariable)
    }

    override fun onResume() {
        super.onResume()
    }


}


interface LifecycleLogger{
    fun registerLifecycleOwner(owner:LifecycleOwner)
}

class LifecycleLoggerImplementation : LifecycleLogger, LifecycleEventObserver{
    override fun registerLifecycleOwner(owner: LifecycleOwner) {

        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            ON_RESUME ->  println("onresume")
            ON_PAUSE ->  println("onpause")
            ON_DESTROY ->  println("ondestroy")
            ON_CREATE ->  println("oncreate")
            ON_START -> println("onstart ")
            ON_STOP -> println("onstop")
            ON_ANY -> println("onany")
        }

    }
}