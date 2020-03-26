package de.wvvh.stayhomeapp.ui.main.debug

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.AchievementStore
import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.actionLogging.KeywordFilter
import kotlinx.android.synthetic.main.fragment_main_debug_action_log.view.*

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
class ActionLogFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main_debug_action_log, container, false)
        val logView: TextView = root.debug_action_log_text
        val filterView: EditText = root.debug_filter_log

        val log: ActionLog = AchievementStore.log
        logView.text = log.toString()

        filterView.doOnTextChanged { text, _, _, _ -> logView.text =
            KeywordFilter(log).filter(text).toString() }

        return root
    }
}