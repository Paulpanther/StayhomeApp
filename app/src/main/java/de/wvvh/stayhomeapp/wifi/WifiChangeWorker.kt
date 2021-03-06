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

/**
 * The Worker will check the connected network each time it is called.
 * It updates the connections and opens a Notification if the user just returned from home
 * Worker is started by [WifiHelper]
 */
class WifiChangeWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        const val TAG = "WifiChangeWorker"
    }

    /**
     * Will be called by the OS
     */
    override fun doWork(): Result {
        val wifi = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val id = wifi.connectionInfo.networkId

        Log.d(TAG, "Connected to network $id")

        val newConnection = Connection(Calendar.getInstance().timeInMillis, id)
        val returned = WifiHelper.updateConnections(newConnection)

        if (returned) {
            NotificationHelper.showNotification(
                applicationContext,
                R.string.notification_not_home_title,
                R.string.notification_not_home_text
            )
        }

        return Result.success()
    }

}