package de.wvvh.stayhomeapp.quests

import de.wvvh.stayhomeapp.actionLogging.Action
import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.actionLogging.IntervalCountAggregator
import java.util.*

/**
 * @author Antonius Naumann
 * @date 22.03.2020
 */
abstract class RepeatingQuestBuilder(override val tag: String, val daysBetween: Int): IQuestBuilder() {
    override fun checkRequirements(log: ActionLog): Boolean {
        val calendar = Calendar.getInstance()
        val endDate = calendar.time
        calendar.add(Calendar.DAY_OF_YEAR, -daysBetween)
        val startDate = calendar.time
        val aggregator = IntervalCountAggregator(getQuestActions(log), startDate, endDate)
        return aggregator.aggregate() == 0
    }
}