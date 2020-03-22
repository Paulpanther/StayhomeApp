package de.wvvh.stayhomeapp.quests

import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.actionLogging.Entry
import de.wvvh.stayhomeapp.actionLogging.TagFilter

interface ISerializedQuest {
    val tag: String
    var solved: Boolean
}

/**
 * @author Antonius Naumann
 * @date 22.03.2020
 */
interface IQuest : ISerializedQuest {
    val exp: Int
    val titleResource: Int
    val descriptionResource: Int
    /**
     * If true, a button should be shown where the user can finish the quest.
     * Only use this for quests which are impossible to track.
     */
    val userVerified: Boolean

    fun check()
}

abstract class IQuestBuilder {
    abstract fun checkRequirements(log: ActionLog): Boolean
    abstract fun createQuest(): IQuest
    abstract val tag: String

    fun getQuestActions(log: ActionLog): List<Entry> {
        return TagFilter(log).filter(tag)
    }

    fun fromSerialized(serializedQuest: ISerializedQuest): IQuest {
        val newQuest = createQuest()
        newQuest.solved = serializedQuest.solved
        return newQuest
    }
}

interface IQuestModule {
    val quests: List<IQuestBuilder>
}