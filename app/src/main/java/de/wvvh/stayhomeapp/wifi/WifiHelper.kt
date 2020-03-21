package de.wvvh.stayhomeapp.wifi

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import de.wvvh.stayhomeapp.ui.NotHomeQuestionActivity
import de.wvvh.stayhomeapp.util.Storage
import io.paperdb.Paper
import java.util.concurrent.TimeUnit

object WifiHelper {

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
            .addTag(WifiChangeWorker.TAG)
            .build()
        val workManager = WorkManager.getInstance()

        workManager.cancelAllWorkByTag(WifiChangeWorker.TAG)
        workManager.enqueue(work)
    }

    fun startHomeQuestionActivityIfNeeded(c: Context) {
        if (isJustReturnedToHome(c)) {
            val intent = Intent(c, NotHomeQuestionActivity::class.java)
            c.startActivity(intent)
        }
    }

    fun storeCurrentId(c: Context) {
        val manager = c.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val id = manager.connectionInfo.networkId
        Paper.book().write(Storage.HOME_WIFI, id)
    }
}