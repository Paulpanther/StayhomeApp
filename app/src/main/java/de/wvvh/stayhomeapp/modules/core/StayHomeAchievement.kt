package de.wvvh.stayhomeapp.modules.core

import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.StreakAchievement
import de.wvvh.stayhomeapp.actionLogging.*
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

        return (aggregator.aggregate(Actions.LEFT_HOME) == 0
                && aggregator.aggregate(Actions.FIRST_APP_START) == 0)
    }

    override val hidden = false
}

object StayHome3Days: StayHomeStreak(3) {
    override val imageResource: Int = R.drawable.ic_icon_stubenhocker_3
    override val titleResource: Int = R.string.core_couch_potato_1_title
    override val descriptionResource: Int = R.string.core_couch_potato_1_desc
    override val explanationResource: Int = R.string.core_couch_potato_1_explanation
}

object StayHome5Days: StayHomeStreak(5) {
    override val imageResource: Int = R.drawable.ic_icon_stubenhocker_5
    override val titleResource: Int = R.string.core_couch_potato_2_title
    override val descriptionResource: Int = R.string.core_couch_potato_2_desc
    override val explanationResource: Int = R.string.core_couch_potato_1_explanation
}

object StayHome7Days: StayHomeStreak(7) {
    override val imageResource: Int = R.drawable.ic_icon_stubenhocker_7
    override val titleResource: Int = R.string.core_couch_potato_3_title
    override val descriptionResource: Int = R.string.core_couch_potato_3_desc
    override val explanationResource: Int = R.string.core_couch_potato_1_explanation
}