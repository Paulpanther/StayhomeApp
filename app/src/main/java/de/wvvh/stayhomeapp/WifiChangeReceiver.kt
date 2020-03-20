package de.wvvh.stayhomeapp

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.*
import android.net.wifi.WifiManager
import android.util.Log

class WifiChangeReceiver : BroadcastReceiver() {

    companion object {
        lateinit var currentIntent: PendingIntent
        var lastId = -1

        fun registerCallback(applicationContext: Context) {
            val manager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build()
            if (::currentIntent.isInitialized) {
                manager.unregisterNetworkCallback(currentIntent)
            }
            currentIntent =
                PendingIntent.getBroadcast(applicationContext,
                    1,
                    Intent(applicationContext, WifiChangeReceiver::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT)
            manager.registerNetworkCallback(network, currentIntent)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val wifi = context?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val id = wifi.connectionInfo.networkId
        if (lastId != id) {
            Log.d("Wifi", "Wifi is " + if (id != -1) id.toString() else "off")
            lastId = id
        }
        registerCallback(context.applicationContext)
    }


}