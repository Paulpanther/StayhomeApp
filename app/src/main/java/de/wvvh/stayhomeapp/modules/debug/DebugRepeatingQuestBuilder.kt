package de.wvvh.stayhomeapp.modules.debug

import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.actionLogging.Action
import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.actionLogging.IntervalCountAggregator
import de.wvvh.stayhomeapp.quests.IQuest
import de.wvvh.stayhomeapp.quests.IQuestBuilder
import java.util.*

/**
 * @author Antonius Naumann
 * @date 22.03.2020
 */
abstract class DebugRepeatingQuestBuilder(val action: Action, val minutesBetween: Int): IQuestBuilder {
    override fun checkRequirements(log: ActionLog): Boolean {
        val calendar = Calendar.getInstance()
        val endDate = calendar.time
        calendar.add(Calendar.MINUTE, -minutesBetween)
        val startDate = calendar.time
        val aggregator = IntervalCountAggregator(log, startDate, endDate)
        return aggregator.aggregate(action) == 0
    }
}

object DebugBackflipQuestBuilder: DebugRepeatingQuestBuilder(Action.DEBUG_BACKFLIP, 3) {
    override fun createQuest(): IQuest = object: IQuest {
        override val exp = 420
        override val descriptionResource = R.string.quest_debug_desc
        override val titleResource = R.string.quest_debug_title
        override val userVerified = true
        override var solved = false
        override fun check() { }
    }
}
