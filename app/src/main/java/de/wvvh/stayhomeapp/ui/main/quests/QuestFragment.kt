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
import de.wvvh.stayhomeapp.ui.main.TabFragment
import kotlinx.android.synthetic.main.fragment_main_quest.view.*

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */

val temporaryQuestList: List<IQuest> = listOf(
    DebugBackflipQuestBuilder.createQuest()
)

data class UserData(val name: String, var level: Int, var iconResource: Int)
val user = UserData("Antonius Naumann", 42, R.drawable.ic_icon_tausendsasse)

// TODO: end of temporary test mockups
class QuestFragment: TabFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main_quest, container, false)
        val questList: RecyclerView = root.quest_list
        questList.adapter = QuestAdapter(temporaryQuestList, user, this.context)
        questList.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false)
        return root
    }
}