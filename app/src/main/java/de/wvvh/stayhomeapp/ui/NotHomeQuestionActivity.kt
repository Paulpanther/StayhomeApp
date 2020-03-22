package de.wvvh.stayhomeapp.ui

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import de.wvvh.stayhomeapp.R
import java.lang.Exception

class NotHomeQuestionActivity : AppCompatActivity() {

    private lateinit var radioReg: MutableMap<Int, String>
    private var activeOAC: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_home_question)
        supportActionBar?.hide()
        radioReg = mutableMapOf()
        addNewMember(R.id.oac_groceries, "einkaufen", "Paul", "Please do the shower quest")
        addNewMember(R.id.oac_test, "test", "KRZ", "kurz")
        addNewMember(R.id.oac_visit_grandma, "visit_grandma", "Visit Grandma", "Do not visit people with high risk!")
        addNewMember(R.id.oac_unnecessary, "unnecessary", "Unnecessary", "Only go out if you have to!")
        addNewMember(R.id.oac_buy_seasoning, "seasoning", "Buy \"Seasoning\"", "We all know what you bought...")
    }

    private fun addNewMember(nameID: Int, stringVal: String, title: String, body: String){
        radioReg[nameID] = stringVal
        findViewById<View>(nameID).findViewById<MaterialTextView>(R.id.headline).setText(title)
        findViewById<View>(nameID).findViewById<MaterialTextView>(R.id.body).setText(body)
    }
    public fun listenerList(view: View){
        if(activeOAC != null) {
            activeOAC!!.findViewById<CheckBox>(R.id.checkBox).isChecked = false
        }

        activeOAC = view
        view.findViewById<CheckBox>(R.id.checkBox).isChecked = true
    }

    private fun getCheckedElement(): String? {
            return radioReg[activeOAC!!.id]
    }

}
