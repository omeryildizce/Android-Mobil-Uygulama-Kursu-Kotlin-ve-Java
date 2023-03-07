package com.omeryildizce.multipleactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String username = "Username";
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

    }

    public void changeActivity(View view){
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        if (!editText.getText().toString().matches(""))
            username = editText.getText().toString();
        intent.putExtra("username",username);
        startActivity(intent);

    }
}