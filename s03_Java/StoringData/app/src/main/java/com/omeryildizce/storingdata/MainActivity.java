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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.omeryildizce.storingdata", Context.MODE_PRIVATE);
        setView();
        int age = 40;
    }

    private void setView() {
        textView = findViewById(R.id.textView);
        editTextNumber = findViewById(R.id.editTextNumber);
        button = findViewById(R.id.buttonKaydet);
    }

    public void save(View view) {
        if (!editTextNumber.getText().toString().matches("")) {
            int age = Integer.parseInt(editTextNumber.getText().toString());

            textView.setText("Your age: " + age);



        }

    }
}