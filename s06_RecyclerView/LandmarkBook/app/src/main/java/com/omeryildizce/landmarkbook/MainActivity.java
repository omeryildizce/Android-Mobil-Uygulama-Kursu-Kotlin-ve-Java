package com.omeryildizce.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Landmark pisa;
    Landmark eiffel;
    Landmark colloseum;
    Landmark londonbridge;
    ArrayList<Landmark> landmarks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Data
        pisa = new Landmark("Pisa", "Italy", R.drawable.pisa);
        eiffel = new Landmark("Eiffel", "France", R.drawable.eiffel);
        colloseum = new Landmark("Colloseum", "Italy", R.drawable.colloseum);
        londonbridge = new Landmark("London Bridge", "England", R.drawable.londonbridge);

        landmarks = new ArrayList<Landmark>();
        landmarks.add(pisa);
        landmarks.add(eiffel);
        landmarks.add(colloseum);
        landmarks.add(londonbridge);
    }
}