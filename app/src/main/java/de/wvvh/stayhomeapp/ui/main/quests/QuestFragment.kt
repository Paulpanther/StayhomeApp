package de.wvvh.stayhomeapp.ui.main.quests

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.modules.debug.DebugBackflipQuestBuilder
import de.wvvh.stayhomeapp.quests.IQuest
import de.wvvh.stayhomeapp.quests.QuestManager
import de.wvvh.stayhomeapp.ui.main.TabFragment
import de.wvvh.stayhomeapp.user.UserDataStore
import kotlinx.android.synthetic.main.fragment_main_quest.view.*

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */

class QuestFragment: TabFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main_quest, container, false)

        // TODO: remove
        UserDataStore.createUser("No Name")

        val questList: RecyclerView = root.quest_list
        questList.adapter = QuestAdapter(
            QuestManager.activeQuests,
            UserDataStore.user,
            this.context)
        questList.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false)
        return root
    }
}