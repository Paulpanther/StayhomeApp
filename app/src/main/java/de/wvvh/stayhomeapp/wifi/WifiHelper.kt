package de.wvvh.stayhomeapp.wifi

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import androidx.annotation.RequiresPermission
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import de.wvvh.stayhomeapp.NotHomeQuestionActivity
import de.wvvh.stayhomeapp.util.Storage
import io.paperdb.Paper
import java.util.concurrent.TimeUnit

object WifiHelper {

    val PERMISSIONS_NEEDED = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    fun isJustReturnedToHome(c: Context): Boolean {
        val connections = Paper.book().read<List<Connection>>(Storage.CONNECTIONS, mutableListOf())
        val home = Paper.book().read<Int>(Storage.HOME_WIFI)

        for (i in connections.size - 1 downTo 1) {
            val current = connections[i]
            val prev = connections[i - 1]
            if (!current.answeredQuestion
                && current.id == home
                && prev.id != home) {
                return true
            }
        }
        return false
    }

    fun enqueueWorker() {
        val work = PeriodicWorkRequestBuilder<WifiChangeWorker>(15, TimeUnit.MINUTES)
            .build()
        val workManager = WorkManager.getInstance()
        workManager.enqueue(work)
    }

    fun startHomeQuestionActivityIfNeeded(c: Context) {
        if (isJustReturnedToHome(c)) {
            val intent = Intent(c, NotHomeQuestionActivity::class.java)
            c.startActivity(intent)
        }
    }

    @RequiresPermission(allOf = [
        "android.permission.ACCESS_WIFI_STATE",
        "android.permission.INTERNET",
        "android.permission.ACCESS_FINE_LOCATION"])
    fun getCurrentWifi(c: Context): Wifi {
        val manager = c.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return Wifi(manager.connectionInfo.ssid, manager.connectionInfo.networkId)
    }
}