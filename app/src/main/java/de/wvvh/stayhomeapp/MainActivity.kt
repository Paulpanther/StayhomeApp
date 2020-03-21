package de.wvvh.stayhomeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.wvvh.stayhomeapp.util.Storage
import de.wvvh.stayhomeapp.wifi.WifiHelper
import io.paperdb.Paper


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Paper.init(applicationContext)

        // TODO replace with actual home wifi
        Paper.book().write(Storage.HOME_WIFI, 72)

        WifiHelper.enqueueWorker()
        WifiHelper.startHomeQuestionActivityIfNeeded(this)
    }
}
