package de.wvvh.stayhomeapp.quests

import de.wvvh.stayhomeapp.achievements.AchievementStore
import de.wvvh.stayhomeapp.actionLogging.Entry
import java.util.*

/**
 * @author Antonius Naumann
 * @date 22.03.2020
 */
object QuestManager {
    private val allQuests: MutableList<IQuestBuilder> = mutableListOf()

    private val _activeQuests: MutableList<IQuest> = mutableListOf()
    val activeQuests: List<IQuest>
        get() = _activeQuests

    fun skipQuest(quest: IQuest) {
        _activeQuests.remove(quest)
        storeActiveQuests()
        val now = Calendar.getInstance().time
        AchievementStore.addEntry(Entry(now, ""))
    }

    fun finishQuest(quest: IQuest) {

    }

    private fun storeActiveQuests() {}

    fun loadModule(module: IQuestModule) = module.quests.forEach { register(it)}
    fun register(element: IQuestBuilder) = allQuests.add(element)
}