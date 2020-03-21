package de.wvvh.stayhomeapp.achievements.core

import de.wvvh.stayhomeapp.achievements.StreakAchievement
import de.wvvh.stayhomeapp.actionLogging.Action
import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.actionLogging.Aggregator
import de.wvvh.stayhomeapp.actionLogging.IntervalCountAggregator
import java.util.*

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
sealed class StayHomeStreak(streakLength: Int): StreakAchievement(streakLength) {
    override fun evaluate(log: ActionLog): Boolean {
        val calendar = Calendar.getInstance()
        val currentDate = calendar.time
        calendar.add(Calendar.DAY_OF_YEAR, -streakLength)
        val startDate = calendar.time

        val aggregator: Aggregator = IntervalCountAggregator(log, startDate, currentDate)

        return aggregator.aggregate(Action.LEFT_HOME) == 0
    }

    override val hidden = false
}

object StayHome3Days: StayHomeStreak(3)
object StayHome5Days: StayHomeStreak(5)
object StayHome7Days: StayHomeStreak(7)