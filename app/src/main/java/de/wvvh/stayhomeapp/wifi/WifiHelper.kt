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

    fun isJustReturnedToHome() = getLastUnquestionedLeave() != null

    fun getLastUnquestionedLeave(): Connection? {
        return Paper.book().read<Connection>(Storage.LAST_UNQUESTIONED_LEAVE)
    }

    fun answeredQuestion() {
        Paper.book().delete(Storage.LAST_UNQUESTIONED_LEAVE)
    }

    fun updateConnections(newConnection: Connection): Boolean {
        val connections = Paper.book().read<MutableList<Connection>>(Storage.CONNECTIONS, mutableListOf())
        val home = Paper.book().read<Int>(Storage.HOME_WIFI)

        var wasLeft = false

        if (connections.isNotEmpty()) {
            if (connections.last().id != home && newConnection.id == home) {
                Paper.book().write(Storage.LAST_UNQUESTIONED_LEAVE, newConnection)
                wasLeft = true
            }
        }
        connections.add(newConnection)
        Paper.book().write(Storage.CONNECTIONS, connections)

        return wasLeft
    }

    fun enqueueWorker() {
        val work = PeriodicWorkRequestBuilder<WifiChangeWorker>(15, TimeUnit.MINUTES)
            .addTag(WifiChangeWorker.TAG)
            .build()
        val workManager = WorkManager.getInstance()

        workManager.cancelAllWorkByTag(WifiChangeWorker.TAG)
        workManager.enqueue(work)
    }

    fun storeCurrentId(c: Context): Boolean {
        val manager = c.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val id = manager.connectionInfo.networkId
        Paper.book().write(Storage.HOME_WIFI, id)
        return true
    }
}