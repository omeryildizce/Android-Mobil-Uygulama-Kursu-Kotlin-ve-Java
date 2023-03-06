package com.omeryildizce.methodsandclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    String TAG = "Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG, "onCreate: ");
        testMethod();

        int toplam = topla(10, 20);
        makeMusicians();
        makeSimpsons();
    }

    private void makeSimpsons() {
        Simpsons homer = new Simpsons("Homer", 25, "Nuclear");
        homer.setAge(26);
        System.out.println(homer.toString());

    }

    private void makeMusicians() {
        Musicians musicians = new Musicians("ali", "piyano", 18);
        musicians.name = "ömer";
        musicians.age = 25;
        musicians.instrument = "gitar";
        musicians.toString();
    }

    private int topla(int a, int b) {
        return a + b;
    }

    public void testMethod() {
        int x = 4 + 4;
        System.out.println(x);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("TAG", "onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}