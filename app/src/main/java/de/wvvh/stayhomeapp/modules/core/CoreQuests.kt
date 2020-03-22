package de.wvvh.stayhomeapp.modules.core

import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.quests.IQuest
import de.wvvh.stayhomeapp.quests.RepeatingQuestBuilder

/**
 * @author Antonius Naumann
 * @date 23.03.2020
 */

object StrollQuestBuilder: RepeatingQuestBuilder("core.stroll",1) {
    override fun createQuest(): IQuest = object: IQuest {
        override val tag = this@StrollQuestBuilder.tag
        override val exp = 250
        override val descriptionResource = R.string.quest_stroll_desc
        override val titleResource = R.string.quest_stroll_title
        override val userVerified = true
        override var solved = false
        override fun check(log: ActionLog) { }
    }
}