package com.omeryildizce.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        new CountDownTimer(10_000, 1_000){
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(String.format("Left: %d", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                textView.setText("Left: "+0);
                textView.setTextColor(Color.RED);
            }
        }.start();
    }
}