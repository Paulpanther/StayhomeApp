package de.wvvh.stayhomeapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.user.UserDataStore

/**
 * Is started on initial launch, asks for user name
 */
class BoardingName : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding_name)
        supportActionBar?.hide()
    }

    fun temp(view: View){
        val text = findViewById<TextInputEditText>(R.id.user_name_text).text.toString()
        if (text != "") {
            UserDataStore.name = text
            startActivity(Intent(this, BoardingWifi::class.java))
            finish()
        }
    }
}
