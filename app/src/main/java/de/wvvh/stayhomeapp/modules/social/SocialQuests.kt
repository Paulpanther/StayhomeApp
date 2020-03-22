package de.wvvh.stayhomeapp.modules.social

import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.quests.IQuest
import de.wvvh.stayhomeapp.quests.RepeatingQuestBuilder

/**
 * @author Antonius Naumann
 * @date 23.03.2020
 */

object TalkativeQuestBuilder: RepeatingQuestBuilder("social.call",1) {
    override fun createQuest(): IQuest = object: IQuest {
        override val tag = this@TalkativeQuestBuilder.tag
        override val exp = 150
        override val descriptionResource = R.string.quest_talkative_desc
        override val titleResource = R.string.quest_talkative_title
        override val userVerified = true
        override var solved = false
        override fun check(log: ActionLog) { }
    }
}