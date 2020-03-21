package de.wvvh.stayhomeapp

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView

class InitialLaunch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_launch)
        supportActionBar?.hide()
    }

    public fun updateWifi(){
        //var tv: TextView = findViewById<TextView>(R.id.wifi_name)
        //tv.setText(Math.random().toString())
    }
    public fun openWiFi(view: View){
        startActivity( Intent(Settings.ACTION_WIFI_SETTINGS));
    }
    override fun onResume(){
        super.onResume()
        updateWifi()
    }
}
