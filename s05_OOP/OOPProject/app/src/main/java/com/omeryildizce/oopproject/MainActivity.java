package com.omeryildizce.oopproject;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User myUser = new User("Ömer", "unemployed");
        myUser.name = "Ömer";
        myUser.job = "Unemployed";

        User newUser = new User("Ali", "Musician");
        newUser.name = "Ali";
        newUser.job = "Musician";
        System.out.println(newUser.information());
        Musician james = new Musician("james", "Guitar", 25);
        // james.age = 65; //private
        // System.out.println(james.age);

        james.setAge(65);
        System.out.println(james.getAge());

        SuperMusician lars = new SuperMusician("lars", "Guitar", 25);
        System.out.println(lars.sing());
        System.out.println(lars.getAge());

        // Polymorphism
        //  Static Polymorphism
        Mathematics mathematics = new Mathematics();
        mathematics.sum(10, 15, 20, 30);
        mathematics.sum(10);
        mathematics.sum(10, 15);
        mathematics.sum();
        //  Dynamic Polymorphism
        Animal animal = new Animal();
        animal.sing();
        Dog dog = new Dog();
        dog.sing();
        dog.test();

        // Abstract and Interface
        String roomName = HouseDecor.roomName;

        Piano mypiano = new Piano();
        Instrument myPiano = new Piano();
        HouseDecor myPiano2 = new Piano();

        mypiano.info();
        mypiano.brand = "Yamaha";
        mypiano.digital =true;

        myPiano.info();
        myPiano2 = mypiano;


    }
}