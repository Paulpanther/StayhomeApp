package de.wvvh.stayhomeapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.wifi.WifiHelper

class NotHomeQuestionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_home_question)
    }

    fun userAnsweredQuestion() {
        WifiHelper.answeredQuestion()
        startActivity(Intent(this, MainActivity::class.java))
    }
}
