package com.omeryildizce.oopdetails;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Kedi kedi1 = new Kedi("kedi","sarı", "beyaz");
        Kedi kedi2 = new Kedi();
        // turIsmi = "test";
        // System.out.println(turIsmi);
        System.out.println(Kedi.turIsmi);
        kedi1.konusedi();
        Kedi.konusediStatic();

        Kopek kopek = new Kopek("fed", "kırmızı","siyah");
        System.out.println(kopek.toString());
    }
}