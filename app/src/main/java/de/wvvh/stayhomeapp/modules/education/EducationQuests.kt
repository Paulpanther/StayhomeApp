package de.wvvh.stayhomeapp.modules.education

import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.quests.IQuest
import de.wvvh.stayhomeapp.quests.RepeatingQuestBuilder

/**
 * @author Antonius Naumann
 * @date 23.03.2020
 */

object BookwormQuestBuilder: RepeatingQuestBuilder("education.book",3) {
    override fun createQuest(): IQuest = object: IQuest {
        override val tag = this@BookwormQuestBuilder.tag
        override val exp = 500
        override val descriptionResource = R.string.quest_bookworm_desc
        override val titleResource = R.string.quest_bookworm_title
        override val userVerified = true
        override var solved = false
        override fun check(log: ActionLog) { }
    }
}