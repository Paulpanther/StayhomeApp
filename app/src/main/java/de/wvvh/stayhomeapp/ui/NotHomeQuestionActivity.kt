package de.wvvh.stayhomeapp.ui

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.AchievementStore
import de.wvvh.stayhomeapp.actionLogging.Action
import de.wvvh.stayhomeapp.actionLogging.Actions
import de.wvvh.stayhomeapp.actionLogging.Entry
import de.wvvh.stayhomeapp.wifi.WifiHelper

data class Question(val title: Int, val body: Int, val necessary: Boolean)

object Questions {
    val SHOPPING = Question(R.string.question_groceries_title,
        R.string.question_groceries_text, true)
}

/**
 * Is started if the user just returned home,
 * asks why the user was outside
 */
class NotHomeQuestionActivity : AppCompatActivity() {

    private var activeOAC: View? = null
    private var map: MutableMap<Int, Question> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_home_question)
        supportActionBar?.hide()

        registerQuestion(R.id.radio_groceries, Questions.SHOPPING)
    }
    private fun registerQuestion(id: Int, question: Question){
        map[id] = question
        findViewById<RadioButton>(id).text = getText(question.title)
    }

    private fun getSelectedQuestion(): Question?{
        return map[findViewById<RadioGroup>(R.id.radio_group_questions).checkedRadioButtonId]
    }

    fun onSubmit(view: View) {
        val question = getSelectedQuestion()
        if (question != null) {
            if (!question.necessary) {
                AchievementStore.addEntry(Entry(action = Actions.LEFT_HOME))
            }
            WifiHelper.answeredQuestion()
            finish()
        }
    }
}
