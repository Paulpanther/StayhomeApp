package de.wvvh.stayhomeapp.actionLogging

import java.util.*

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
abstract class Aggregator(val log: List<Entry>) {
    /**
     * Takes an action and represents it by a number
     */
    abstract fun aggregate(action: Action): Int

    abstract fun aggregate(): Int
}

class CountAggregator(log: ActionLog): Aggregator(log) {
    override fun aggregate(action: Action) = log.count { (_, event) -> event == action }
    override fun aggregate() = log.size
}

class IntervalCountAggregator(
    log: List<Entry>,
    val start: Date,
    val end: Date = Calendar.getInstance().time) : Aggregator(log) {

    override fun aggregate(action: Action) = log.count {
            (timestamp, event) -> timestamp > start && timestamp < end && event == action
    }

    override fun aggregate() = log.count {
            (timestamp, _) -> timestamp > start && timestamp < end
    }
}

class TriggerAggregator(log: ActionLog): Aggregator(log) {
    override fun aggregate(action: Action): Int = if(log.last().action == action) 1 else 0
    override fun aggregate(): Int = 0
}
