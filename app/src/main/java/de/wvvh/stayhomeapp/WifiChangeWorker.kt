package de.wvvh.stayhomeapp

import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class WifiChangeWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        val wifi = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        Log.d("Bla", wifi.connectionInfo.networkId.toString())
        NotificationHelper.showNotification(applicationContext, wifi.connectionInfo.networkId)
        return Result.success()
    }

}