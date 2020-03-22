package de.wvvh.stayhomeapp.ui

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.AchievementStore
import de.wvvh.stayhomeapp.actionLogging.Action
import de.wvvh.stayhomeapp.actionLogging.Entry

data class Question(val title: Int, val body: Int, val necessary: Boolean)

object Questions {
    val SHOPPING = Question(R.string.question_groceries_title,
        R.string.question_groceries_text, true)
}

class NotHomeQuestionActivity : AppCompatActivity() {

    private lateinit var radioReg: MutableMap<Int, Question>
    private var activeOAC: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_home_question)
        supportActionBar?.hide()
        radioReg = mutableMapOf()
        addNewMember(R.id.oac_groceries, Questions.SHOPPING)
    }

    private fun addNewMember(nameID: Int, question: Question){
        radioReg[nameID] = question
        findViewById<View>(nameID).findViewById<MaterialTextView>(R.id.headline).setText(question.title)
        findViewById<View>(nameID).findViewById<MaterialTextView>(R.id.body).setText(question.body)
    }

    fun listenerList(view: View){
        if(activeOAC != null) {
            activeOAC!!.findViewById<CheckBox>(R.id.checkBox).isChecked = false
        }

        activeOAC = view
        view.findViewById<CheckBox>(R.id.checkBox).isChecked = true
    }

    private fun getCheckedElement(): Question? {
        return radioReg[activeOAC!!.id]
    }

    fun onSubmit(view: View) {
        val question = getCheckedElement()
        if (question != null) {
            if (!question.necessary) {
                AchievementStore.addEntry(Entry(action = Action.LEFT_HOME))
            }
            finish()
        }
        // TODO User: Please choose something
    }

}
