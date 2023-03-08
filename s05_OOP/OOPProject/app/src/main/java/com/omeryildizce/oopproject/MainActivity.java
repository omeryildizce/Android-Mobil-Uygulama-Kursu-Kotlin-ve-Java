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

        Musician james = new Musician("james", "Guitar", 25);
        // james.age = 65; //private
        // System.out.println(james.age);

        james.setAge(65);
        System.out.println(james.getAge());

        SuperMusician lars = new SuperMusician("lars", "Guitar", 25);
        System.out.println(lars.sing());
        System.out.println(lars.getAge());
    }
}