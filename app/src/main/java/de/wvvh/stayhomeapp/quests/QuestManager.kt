package de.wvvh.stayhomeapp.quests

import de.wvvh.stayhomeapp.achievements.AchievementStore
import de.wvvh.stayhomeapp.actionLogging.Action
import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.actionLogging.Entry
import de.wvvh.stayhomeapp.util.Storage
import io.paperdb.Paper
import java.lang.IllegalStateException
import de.wvvh.stayhomeapp.user.UserDataStore
import java.util.*

/**
 * @author Antonius Naumann
 * @date 22.03.2020
 */
object QuestManager {
    private val allQuests: MutableList<IQuestBuilder> = mutableListOf()

    private lateinit var _activeQuests: MutableList<IQuest>
    val activeQuests: List<IQuest>
        get() {
            if (!::_activeQuests.isInitialized) {
                _activeQuests = loadActiveQuests()
            }
            return _activeQuests
        }

    init {
        AchievementStore.log.addObserver(this::updateQuests)
    }

    fun skipQuest(quest: IQuest) {
        if(_activeQuests.remove(quest)) {
            storeActiveQuests()
            val now = Calendar.getInstance().time
            AchievementStore.addEntry(Entry(now, Action(quest.tag, "skipped")))
        }
    }

    fun finishQuest(quest: IQuest) {
        if(_activeQuests.remove(quest)) {
            storeActiveQuests()
            val now = Calendar.getInstance().time
            AchievementStore.addEntry(Entry(now, Action(quest.tag, "finished")))
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
        storeActiveQuests()
        newQuests.forEach{
            val now = Calendar.getInstance().time
            AchievementStore.addEntry(Entry(now, Action(it.tag,  "activated")))
        }
    }

    private fun storeActiveQuests() {
        val serialized = _activeQuests.map { it as ISerializedQuest }
        Paper.book().write(Storage.ACTIVE_QUESTS, serialized)
    }

    private fun loadActiveQuests(): MutableList<IQuest> {
        val serialized = Paper.book().read<MutableList<ISerializedQuest>>(Storage.ACTIVE_QUESTS, mutableListOf())
        return serialized.map {
            val builder = allQuests.find { builder -> builder.tag == it.tag }
            builder?.fromSerialized(it) ?: throw IllegalStateException("")
        }.toMutableList()
    }

    fun updateQuests(log: ActionLog) {
        _activeQuests.forEach{ it.check(log) }
        storeActiveQuests()
    }
    fun loadModule(module: IQuestModule) = module.quests.forEach { register(it)}
    fun register(element: IQuestBuilder) = allQuests.add(element)
}