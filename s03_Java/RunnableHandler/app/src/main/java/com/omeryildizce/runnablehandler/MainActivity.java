package com.omeryildizce.runnablehandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    int second;
    int minute;
    int hour;
    Runnable runnable;
    Handler handler;
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        second = 0;
        minute = 0;
        hour = 0;
        buttonStart = findViewById(R.id.buttonStart);
    }

    public void start(View view) {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                textView.setText(String.format("Time: %02d:%02d:%02d", hour, minute, second));
                second++;
                if (second % 60 == 0) {
                    minute++;
                    second = 0;
                    if (minute % 60 == 0) {
                        hour++;
                        minute = 0;
                    }
                }
                handler.postDelayed(runnable, 1_000);
            }
        };
        handler.post(runnable);
        buttonStart.setEnabled(false);
    }

    public void stop(View view) {
        buttonStart.setEnabled(true);
        handler.removeCallbacks(runnable);
        second = 0;
        minute = 0;
        hour = 0;
    }
}