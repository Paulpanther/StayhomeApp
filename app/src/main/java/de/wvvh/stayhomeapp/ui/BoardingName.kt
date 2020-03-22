package de.wvvh.stayhomeapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.TextValueSanitizer
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.user.UserData
import de.wvvh.stayhomeapp.user.UserDataStore

class BoardingName : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding_name)
        supportActionBar?.hide()
    }

    fun temp(view: View){
        val text = findViewById<TextInputEditText>(R.id.user_name_text).text.toString()
        if (text != "") {
            UserDataStore.createUser(text)
            startActivity(Intent(this, InitialLaunch::class.java))
            finish()
        }
    }
}
