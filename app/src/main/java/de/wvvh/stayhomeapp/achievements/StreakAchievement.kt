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
abstract class StreakAchievement(val streakLength: Int): Achievement()