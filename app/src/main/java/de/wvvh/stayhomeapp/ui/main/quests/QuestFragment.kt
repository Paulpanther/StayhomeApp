package de.wvvh.stayhomeapp.ui.main.quests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.quests.IQuest
import de.wvvh.stayhomeapp.ui.main.TabFragment
import kotlinx.android.synthetic.main.fragment_main_quest.view.*


/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */

// TODO: remove temporary quest mockups
object exampleQuest: IQuest {
    override val exp = 420
    override val titleResource = R.string.quest_sample_title
    override val descriptionResource = R.string.quest_sample_desc
    override val userVerified = false
}

object userVerfiedQuest: IQuest {
    override val exp = 420
    override val titleResource = R.string.quest_sample_title
    override val descriptionResource = R.string.quest_sample_desc
    override val userVerified = true
}

val temporaryQuestList: List<IQuest> = listOf(
    userVerfiedQuest,
    userVerfiedQuest,
    exampleQuest,
    exampleQuest,
    userVerfiedQuest,
    exampleQuest,
    userVerfiedQuest
)

class QuestFragment: TabFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main_quest, container, false)
        val questList: RecyclerView = root.quest_list
        questList.adapter = QuestAdapter(temporaryQuestList)
        questList.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false)
        questList.addItemDecoration(
            QuestHeaderDecoration(this.context, questList, R.layout.level_card)
        )
        return root
    }
}