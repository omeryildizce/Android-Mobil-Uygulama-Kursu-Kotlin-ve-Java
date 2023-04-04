package com.omeryildizce.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.*
import com.omeryildizce.workmanager.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val data = Data.Builder().putInt("intKey", 1).build()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging( false)
            .setRequiresBatteryNotLow(true)
            .build()

        /*
        val myWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<RefreshDatabase>()
            .setConstraints(constraints)
            .setInputData(data)
            .setInitialDelay(30, TimeUnit.SECONDS)
            .addTag("myTag")
            .build()

        WorkManager.getInstance(this).enqueue(myWorkRequest)

         */

        val myRequest: PeriodicWorkRequest = PeriodicWorkRequestBuilder<RefreshDatabase>(1, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        WorkManager.getInstance(this).enqueue(myRequest)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(myRequest.id)
            .observe(this, Observer {
                if (it.state == WorkInfo.State.RUNNING){
                    println("running")
                }else if(it.state == WorkInfo.State.FAILED)
                {
                    println("failed")
                }else if (it.state == WorkInfo.State.SUCCEEDED){
                    println("succeeded")
                }
            })


        // Chaining
        val oneTimeRequest = OneTimeWorkRequestBuilder<RefreshDatabase>()
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        WorkManager.getInstance(this).beginWith(oneTimeRequest)
            .then(oneTimeRequest)
            .then(oneTimeRequest)
            .enqueue()

    // WorkManager.getInstance(this).cancelAllWork()
    }
}