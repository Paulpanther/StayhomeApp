package de.wvvh.stayhomeapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.util.Storage
import de.wvvh.stayhomeapp.wifi.WifiHelper
import io.paperdb.Paper

class InitialLaunch : AppCompatActivity() {

    companion object {
        fun isFirstLaunch(): Boolean {
            return Paper.book().read(Storage.IS_FIRST_LAUNCH, true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_launch)
        supportActionBar?.hide()
    }

    fun setHomeWifi(view: View) {
        val success = WifiHelper.storeCurrentId(applicationContext)
        if (success) {
            Paper.book().write(Storage.IS_FIRST_LAUNCH, false)
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            // TODO Dann mach es doch selber
        }
    }

    fun openWiFi(view: View){
        startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
    }
}
