package de.wvvh.stayhomeapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import de.wvvh.stayhomeapp.R

class BoardingName : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding_name)
        supportActionBar?.hide()
    }
    public fun temp(view: View){
        startActivity(Intent(this, InitialLaunch::class.java))
    }
}
