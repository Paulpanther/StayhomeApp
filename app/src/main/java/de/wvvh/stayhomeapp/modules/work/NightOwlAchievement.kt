package de.wvvh.stayhomeapp.modules.work

import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.IAchievement
import de.wvvh.stayhomeapp.actionLogging.ActionLog

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
    override fun evaluate(log: ActionLog): Boolean = false
}