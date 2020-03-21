package de.wvvh.stayhomeapp.actionLogging

import java.util.*

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
abstract class Aggregator(val log: ActionLog) {
    /**
     * Takes an action and represents it by a number
     */
    abstract fun aggregate(action: Action): Int
}

class CountAggregator(log: ActionLog): Aggregator(log) {
    override fun aggregate(action: Action) = log.count { (_, event) -> event == action }
}

class IntervalCountAggregator(log: ActionLog, val start: Date, val end: Date): Aggregator(log) {
    override fun aggregate(action: Action) = log.count {
            (timestamp, event) -> timestamp > start && timestamp < end && event == action
    }
}

class TriggerAggregator(log: ActionLog): Aggregator(log) {
    override fun aggregate(action: Action): Int = if(log.last().action == action) 1 else 0
}

