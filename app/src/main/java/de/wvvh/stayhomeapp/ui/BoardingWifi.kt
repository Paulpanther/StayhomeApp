package de.wvvh.stayhomeapp.ui

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.util.Storage
import de.wvvh.stayhomeapp.wifi.WifiHelper
import io.paperdb.Paper

/**
 * Is started on initial launch after [BoardingName], asks for home wifi
 */
class BoardingWifi : AppCompatActivity() {

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
            finish()
        } else {


            var ma: MaterialAlertDialogBuilder = MaterialAlertDialogBuilder(this)
            val listener: OnClickListener = object: OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                }
            }
                ma.setTitle(R.string.wifi_disabled_title)
                ma.setMessage(R.string.wifi_disabled_message)
                ma.setPositiveButton("Ok", null)
                ma.setNegativeButton(R.string.button_open_wifi_settings, listener)
                ma.show();
        }
    }

    fun openWiFi(view: View?){
        startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
    }
}
