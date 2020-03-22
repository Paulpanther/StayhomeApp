package de.wvvh.stayhomeapp.quests

import de.wvvh.stayhomeapp.actionLogging.Action
import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.actionLogging.Entry
import de.wvvh.stayhomeapp.actionLogging.TagFilter

/**
 * @author Antonius Naumann
 * @date 22.03.2020
 */
interface IQuest {
    val tag: String
    val exp: Int
    val titleResource: Int
    val descriptionResource: Int
    /**
     * If true, a button should be shown where the user can finish the quest.
     * Only use this for quests which are impossible to track.
     */
    val userVerified: Boolean
    var solved: Boolean

    fun check()
}

abstract class IQuestBuilder {
    abstract fun checkRequirements(log: ActionLog): Boolean
    abstract fun createQuest(): IQuest
    abstract val tag: String

    fun getQuestActions(log: ActionLog): List<Entry> {
        return TagFilter(log).filter(tag).map {
            val name = it.action
            val shorterName = Action(name.substring(tag.length + 1))
            Entry(it.date, shorterName)
        }
    }
}

interface IQuestModule {
    val quests: List<IQuestBuilder>
}