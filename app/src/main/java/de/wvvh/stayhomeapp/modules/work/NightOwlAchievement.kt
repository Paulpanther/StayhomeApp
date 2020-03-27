package de.wvvh.stayhomeapp.modules.work

import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.IAchievement
import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.actionLogging.Actions
import de.wvvh.stayhomeapp.actionLogging.RepeatingIntervalCountAggregator
import java.util.*

/**
 * @author Antonius Naumann
 * @date 22.03.2020
 */
object NightOwlAchievement: IAchievement {
    override val imageResource: Int = R.drawable.ic_icon_nachteule
    override val titleResource: Int = R.string.work_night_owl_title
    override val descriptionResource: Int = R.string.work_night_owl_desc
    override val explanationResource: Int = R.string.work_night_owl_explanation
    override val hidden: Boolean = true
    override fun evaluate(log: ActionLog): Boolean {
        val filter = RepeatingIntervalCountAggregator(log, Calendar.HOUR_OF_DAY, 2..5)
        return filter.aggregate(Actions.APP_START) + filter.aggregate(Actions.APP_RESUME) > 0
    }
}