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

    /**
     * Represents this action log by a number
     */
    abstract fun aggregate(): Int
}

class CountAggregator(log: ActionLog): Aggregator(log) {
    override fun aggregate(action: Action) = log.count { (_, event) -> event == action }
    override fun aggregate() = log.size
}

/**
 * Counts all requested actons which are in between start and end date (both exclusive)
 * @param log
 * @param start of requested date range (exclusive)
 * @param end of requested date range (exclusive)
 *
 * @author Antonius Naumann
 * @date 21.03.2020
 */
class IntervalCountAggregator(
    log: List<Entry>,
    val start: Date,
    val end: Date = Calendar.getInstance().time): Aggregator(log) {

    override fun aggregate(action: Action) = log.count {
            (timestamp, event) -> timestamp > start && timestamp < end && event == action
    }

    override fun aggregate() = log.count {
            (timestamp, _) -> timestamp > start && timestamp < end
    }
}

/**
 * Counts the occurrences of all matching actions which have the specified calendar field in range
 * @param log
 * @param calendarField Int identifier for the specified java calender field e.g. Calendar.HOUR_OF_DAY
 *
 * @author Antonius Naumann
 * @date 21.03.2020
 */
class RepeatingIntervalCountAggregator(
    log: ActionLog,
    val calendarField: Int,
    val range: IntRange):  Aggregator(log) {

    override fun aggregate(action: Action): Int = log.filter { entry ->
            val calendar = Calendar.getInstance()
            calendar.time = entry.date
            val calendarField = calendar.get(Calendar.HOUR_OF_DAY)
            action == entry.action && range.contains(calendarField)
        }.size

    override fun aggregate(): Int = log.filter { (date, _) ->
            val calendar = Calendar.getInstance()
            calendar.time = date
            val calendarField = calendar.get(Calendar.HOUR_OF_DAY)
            range.contains(calendarField)
        }.size


}

/**
 * Just checks if the most recent logged action is the requested action
 * @param log
 *
 * @author Antonius Naumann
 * @date 21.03.2020
 */
class TriggerAggregator(log: ActionLog): Aggregator(log) {
    override fun aggregate(action: Action): Int = if(log.last().action == action) 1 else 0
    override fun aggregate(): Int = 1
}
