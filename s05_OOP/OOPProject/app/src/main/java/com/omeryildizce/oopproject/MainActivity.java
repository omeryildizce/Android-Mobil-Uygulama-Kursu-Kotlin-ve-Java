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



    }
}