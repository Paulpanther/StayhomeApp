package de.wvvh.stayhomeapp.ui

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import de.wvvh.stayhomeapp.R

class NotHomeQuestionActivity : AppCompatActivity() {

    private lateinit var radioReg: MutableMap<Int, String>
    private lateinit var activeOAC: View
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_home_question)
        supportActionBar?.hide()
        radioReg = mutableMapOf()
        addNewMember(R.id.oac_groceries, "einkaufen")
        addNewMember(R.id.oac_test, "test")
    }

    private fun addNewMember(nameID: Int, stringVal: String){
        radioReg[nameID] = stringVal
    }
    public fun listenerList(view: View){
        activeOAC = view
    }

}
