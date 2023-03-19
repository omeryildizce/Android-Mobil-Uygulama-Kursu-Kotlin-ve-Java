package com.omeryildizce.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Data data = new Data.Builder().putInt("intKey", 1).build();
        Constraints constraints = new Constraints.Builder()
                //.setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(false)
                .build();
        WorkRequest workRequest = new OneTimeWorkRequest.Builder(RefreshDaatabase.class)
                .setConstraints(constraints)
                .setInputData(data)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .addTag("myTag")
                .build();

        WorkManager.getInstance(this).enqueue(workRequest);
    }
}