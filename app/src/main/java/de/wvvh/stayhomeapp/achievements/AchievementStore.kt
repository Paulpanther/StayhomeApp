package de.wvvh.stayhomeapp.achievements

import de.wvvh.stayhomeapp.actionLogging.Action
import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.actionLogging.Aggregator
import de.wvvh.stayhomeapp.actionLogging.IntervalCountAggregator
import java.util.*

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
object AchievementStore {
    private val achievements: MutableList<Achievement> = mutableListOf()

    fun register(element: Achievement) = achievements.add(element)
}