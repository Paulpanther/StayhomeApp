package de.wvvh.stayhomeapp.quests

import de.wvvh.stayhomeapp.actionLogging.ActionLog

/**
 * @author Antonius Naumann
 * @date 22.03.2020
 */
interface IQuest {
    val exp: Int
    val titleResource: Int
    val descriptionResource: Int
    /**
     * If true, a button should be shown where the user can finish the quest.
     * Only use this for quests which are impossible to track.
     */
    val userVerified: Boolean
}

interface IQuestBuilder {
    fun checkRequirements(log: ActionLog): Boolean
    fun createQuest(): IQuest
}

interface IQuestModule {
    val quests: List<IQuestBuilder>
}