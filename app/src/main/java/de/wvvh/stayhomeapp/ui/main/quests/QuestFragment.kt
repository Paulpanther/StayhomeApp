package de.wvvh.stayhomeapp.ui.main.quests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.quests.QuestManager
import de.wvvh.stayhomeapp.user.UserDataStore
import kotlinx.android.synthetic.main.fragment_main_quest.view.*

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */

class QuestFragment: Fragment() {

    lateinit var adapter: QuestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main_quest, container, false)


        val questList: RecyclerView = root.quest_list

        adapter = QuestAdapter(
            QuestManager.activeQuests,
            UserDataStore,
            this.context)
        questList.adapter = adapter
        questList.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false)
        return root
    }
}