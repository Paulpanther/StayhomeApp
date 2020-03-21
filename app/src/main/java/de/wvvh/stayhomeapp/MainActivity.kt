package de.wvvh.stayhomeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import de.wvvh.stayhomeapp.util.StorageManager
import de.wvvh.stayhomeapp.wifi.WifiChangeWorker
import de.wvvh.stayhomeapp.wifi.WifiHelper
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO replace with actual home wifi
        StorageManager(this).homeWifi.set(72)

        WifiHelper.enqueueWorker()
        WifiHelper.startHomeQuestionActivityIfNeeded(this)
    }
}
