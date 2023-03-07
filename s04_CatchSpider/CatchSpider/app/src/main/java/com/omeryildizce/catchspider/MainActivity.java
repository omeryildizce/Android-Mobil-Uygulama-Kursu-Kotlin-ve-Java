package com.omeryildizce.catchspider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timeText ;
    TextView scoreText ;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        score = 0;
    }

    public void increaseScore(View view){
        score++;
        scoreText.setText(getResources().getString(R.string.score_00) + " "+ score);
    }
}