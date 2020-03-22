package de.wvvh.stayhomeapp.quests

import de.wvvh.stayhomeapp.achievements.AchievementStore
import de.wvvh.stayhomeapp.actionLogging.Action
import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.actionLogging.Entry
import de.wvvh.stayhomeapp.user.UserData
import de.wvvh.stayhomeapp.user.UserDataStore
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

    init {
        AchievementStore.log.addObserver(this::updateQuests)
    }

    fun skipQuest(quest: IQuest) {
        if(_activeQuests.remove(quest)) {
            storeActiveQuests()
            val now = Calendar.getInstance().time
            AchievementStore.addEntry(Entry(now, Action(quest.tag, ":skipped")))
        }
    }

    fun finishQuest(quest: IQuest) {
        if(_activeQuests.remove(quest)) {
            storeActiveQuests()
            val now = Calendar.getInstance().time
            AchievementStore.addEntry(Entry(now, Action(quest.tag, ":finished")))
            UserDataStore.user.xp += quest.exp
        }
    }

    fun loadIntoActive() {
        val newQuests = allQuests.filter {
            val isActive = activeQuests.find { active -> it.tag.toString() == active.tag } != null
            !isActive && it.checkRequirements(AchievementStore.log) }
            .map {
                it.createQuest() }
        _activeQuests.addAll(newQuests)
        newQuests.forEach{
            val now = Calendar.getInstance().time
            AchievementStore.addEntry(Entry(now, Action(it.tag,  ":activated")))
        }
    }

    private fun storeActiveQuests() {}

    fun updateQuests(log: ActionLog) = _activeQuests.forEach{ it.check(log) }
    fun loadModule(module: IQuestModule) = module.quests.forEach { register(it)}
    fun register(element: IQuestBuilder) = allQuests.add(element)
}