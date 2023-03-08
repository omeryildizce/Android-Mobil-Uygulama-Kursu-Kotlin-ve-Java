package com.omeryildizce.catchspider;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timeText;
    TextView scoreText;
    int score;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArrays;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageArrays = new ImageView[]{
                imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9
        };

        hideImages();
        score = 0;
        new CountDownTimer(10_000, 1_000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: " + (millisUntilFinished / 1_000));
            }

            @Override
            public void onFinish() {
                timeText.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArrays) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game? ");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Score: " + score+"\nGame Over!", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();
            }
        }.start();
    }

    public void increaseScore(View view) {
        score++;
        scoreText.setText(getResources().getString(R.string.score_00) + " " + score);
    }

    public void hideImages() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArrays) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int index = random.nextInt(9);
                imageArrays[index].setVisibility(View.VISIBLE);
                handler.postDelayed(this, 500);
            }
        };
        handler.post(runnable);
    }
}