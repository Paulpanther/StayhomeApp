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

/**
 * Logic to check if the user was not home
 *
 * All connection data is stored persistent in [Storage.CONNECTIONS]
 * The last connection which marks a return home for which the user wasn't asked about is stored
 * int [Storage.LAST_UNQUESTIONED_LEAVE]
 */
object WifiHelper {

    fun isJustReturnedToHome() = getLastUnquestionedLeave() != null

    fun getLastUnquestionedLeave(): Connection? {
        return Paper.book().read<Connection>(Storage.LAST_UNQUESTIONED_LEAVE)
    }

    /**
     * The user answered why they were away and the connection can be deleted
     */
    fun answeredQuestion() {
        Paper.book().delete(Storage.LAST_UNQUESTIONED_LEAVE)
    }

    /**
     * Adds the [newConnection] to the persistent stored list of connections
     * @returns true if the connection marks a return to home
     */
    fun updateConnections(newConnection: Connection): Boolean {
        val connections = Paper.book().read<MutableList<Connection>>(Storage.CONNECTIONS, mutableListOf())
        val home = Paper.book().read<Int>(Storage.HOME_WIFI)

        var wasLeft = false

        // Check if user returned home
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
        // 15 minutes interval is minimum
        val work = PeriodicWorkRequestBuilder<WifiChangeWorker>(15, TimeUnit.MINUTES)
            .addTag(WifiChangeWorker.TAG)
            .build()
        val workManager = WorkManager.getInstance()

        workManager.cancelAllWorkByTag(WifiChangeWorker.TAG)
        workManager.enqueue(work)
    }

    /**
     * Stores the current network id in [Storage.HOME_WIFI]
     * @returns false if there is no connection, else true
     */
    fun storeCurrentId(c: Context): Boolean {
        val manager = c.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val id = manager.connectionInfo.networkId
        return if (id == -1) {
            false
        } else {
            Paper.book().write(Storage.HOME_WIFI, id)
            true
        }
    }
}