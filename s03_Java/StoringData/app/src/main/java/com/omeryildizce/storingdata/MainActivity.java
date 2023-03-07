package com.omeryildizce.storingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editTextNumber;
    Button button;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setView();
        int age = sharedPreferences.getInt("age", 0);
        textView.setText("Your age: " + age);
    }

    private void setView() {
        textView = findViewById(R.id.textView);
        editTextNumber = findViewById(R.id.editTextNumber);
        button = findViewById(R.id.buttonKaydet);
        sharedPreferences = this.getSharedPreferences("com.omeryildizce.storingdata", Context.MODE_PRIVATE);
    }


    public void save(View view) {
        if (!editTextNumber.getText().toString().matches("")) {
            int age = Integer.parseInt(editTextNumber.getText().toString());

            textView.setText("Your age: " + age);
            sharedPreferences.edit().putInt("age", age).apply();


        }

    }

    public void delete(View view){
        int age = sharedPreferences.getInt("age",0);
        if (age != 0){
            sharedPreferences.edit().remove("age").apply();
            textView.setText("Your age: " + age);
        }
    }
}