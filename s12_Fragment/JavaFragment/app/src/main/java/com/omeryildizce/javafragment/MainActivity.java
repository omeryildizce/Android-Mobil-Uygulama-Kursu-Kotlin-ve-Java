package com.omeryildizce.javafragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToFirst(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FirstFragment firstFragment = new FirstFragment();
       // fragmentTransaction.add(R.id.frameLayout, firstFragment).commit();
        fragmentTransaction.replace(R.id.frameLayout, firstFragment).commit();

    }

    public void goToSecond(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SecondFragment secondFragment = new SecondFragment();
      //  fragmentTransaction.add(R.id.frameLayout, secondFragment).commit();
        fragmentTransaction.replace(R.id.frameLayout, secondFragment).commit();
    }
}