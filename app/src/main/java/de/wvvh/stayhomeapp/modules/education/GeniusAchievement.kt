package de.wvvh.stayhomeapp.modules.education

import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.IAchievement
import de.wvvh.stayhomeapp.actionLogging.ActionLog


// TODO: add logic to evaluate
/**
 * @author Antonius Naumann
 * @date 22.03.2020
 */
object GeniusAchievement: IAchievement {
    override val imageResource: Int = R.drawable.ic_icon_tausendsassa
    override val titleResource: Int = R.string.education_genius_title
    override val descriptionResource: Int = R.string.education_genius_desc
    override val explanationResource: Int = R.string.education_genius_explanation
    override val hidden: Boolean = true
    override fun evaluate(log: ActionLog): Boolean = false
}