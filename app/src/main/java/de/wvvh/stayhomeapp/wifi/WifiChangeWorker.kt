package de.wvvh.stayhomeapp.wifi

import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import de.wvvh.stayhomeapp.*
import de.wvvh.stayhomeapp.util.NotificationHelper
import de.wvvh.stayhomeapp.util.Storage
import io.paperdb.Paper
import java.util.*

class WifiChangeWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        const val TAG = "WifiChangeWorker"
    }

    override fun doWork(): Result {
        val wifi = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val id = wifi.connectionInfo.networkId

        Log.d(TAG, "Connected to network $id")

        val connections = Paper.book().read<MutableList<Connection>>(Storage.CONNECTIONS, mutableListOf())
        connections.add(Connection(Calendar.getInstance().timeInMillis, id))
        Paper.book().write(Storage.CONNECTIONS, connections)

        if (WifiHelper.isJustReturnedToHome(applicationContext)) {
            NotificationHelper.showNotification(
                applicationContext,
                R.string.notification_not_home_title,
                R.string.notification_not_home_text
            )
        }

        return Result.success()
    }

}