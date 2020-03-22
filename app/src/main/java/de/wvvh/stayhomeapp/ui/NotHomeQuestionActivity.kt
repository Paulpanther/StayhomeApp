package de.wvvh.stayhomeapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.wifi.WifiHelper

class NotHomeQuestionActivity : AppCompatActivity() {

    lateinit private var radioReg: MutableMap<Int, String>
    lateinit private var rg: RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_home_question)
        supportActionBar?.hide()
        radioReg = mutableMapOf()
        rg = findViewById<RadioGroup>(R.id.adventure_radio)
        setupRadio()
    }
    private fun setupRadio(){
        addNewMember(R.string.rb_einkaufen)
    }
    private fun addNewMember(nameID: Int){
        var rb: RadioButton = RadioButton(this)
        rb.setText(nameID)
        radioReg[rb.id] = rb.text.toString()
        rg.addView(rb)
    }
    public fun printSelectedVal(view: View){
        var temp: TextView = findViewById(R.id.temp_out)
        temp.setText(findViewById<RadioButton>(rg.checkedRadioButtonId).text)
    }

    fun userAnsweredQuestion() {
        WifiHelper.answeredQuestion()
        startActivity(Intent(this, MainActivity::class.java))
    }
}
