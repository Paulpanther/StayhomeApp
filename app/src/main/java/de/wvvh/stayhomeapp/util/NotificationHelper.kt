package de.wvvh.stayhomeapp.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.ui.MainActivity

object NotificationHelper {

    private const val CHANNEL_ID = "de.wwvh.stayhomeapp"

    fun showNotification(c: Context, title: Int, message: Int) {
        showNotification(
            c,
            c.getText(title) as String,
            c.getText(message) as String
        )
    }

    fun showNotification(c: Context, title: String, message: String) {
        val intent = Intent(c, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            c, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val notificationBuilder =
            NotificationCompat.Builder(c,
                CHANNEL_ID
            )
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

        val notificationManager =
            c.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                c.getText(R.string.notification_channel),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }
}