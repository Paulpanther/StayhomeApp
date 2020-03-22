package de.wvvh.stayhomeapp.modules.social

import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.IAchievement
import de.wvvh.stayhomeapp.actionLogging.ActionLog

// TODO: add logic to evaluate
/**
 * @author Antonius Naumann
 * @date 22.03.2020
 */
object SweetheartAchievement: IAchievement {
    override val imageResource: Int = R.drawable.ic_icon_oma
    override val titleResource: Int = R.string.social_sweetheart_title
    override val descriptionResource: Int = R.string.social_sweetheart_desc
    override val explanationResource: Int = R.string.social_sweetheart_explanation
    override val hidden: Boolean = true
    override fun evaluate(log: ActionLog): Boolean = false
}