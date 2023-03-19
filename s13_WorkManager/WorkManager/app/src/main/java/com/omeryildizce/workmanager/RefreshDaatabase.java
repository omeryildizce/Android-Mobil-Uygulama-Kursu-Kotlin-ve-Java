package com.omeryildizce.workmanager;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RefreshDaatabase extends Worker {
    Context myContext;

    public RefreshDaatabase(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.myContext = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData();
        int myNumber = data.getInt("intKey", 0);
        refreshDaatabase(myNumber);
        return Result.success();
    }

    private void refreshDaatabase(int myNumber) {
        SharedPreferences sharedPreferences = myContext.getSharedPreferences("com.omeryildizce.workmanager", Context.MODE_PRIVATE);
        int mySavedNumber = sharedPreferences.getInt("myNumber", 0);
        mySavedNumber += myNumber;
        System.out.println(mySavedNumber);
        sharedPreferences.edit().putInt("myNumber",mySavedNumber).apply();
    }
}
