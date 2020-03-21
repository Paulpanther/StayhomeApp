package de.wvvh.stayhomeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val work = PeriodicWorkRequestBuilder<WifiChangeWorker>(15, TimeUnit.MINUTES)
            .build()
        val workManager = WorkManager.getInstance()
        workManager.enqueue(work)
    }
}
