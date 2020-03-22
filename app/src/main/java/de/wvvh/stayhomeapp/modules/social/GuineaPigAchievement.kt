package de.wvvh.stayhomeapp.modules.social

import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.IAchievement
import de.wvvh.stayhomeapp.actionLogging.ActionLog

// TODO: add logic to evaluate
/**
 * @author Antonius Naumann
 * @date 22.03.2020
 */
object GuineaPigAchievement: IAchievement {
    override val imageResource: Int = R.drawable.ic_icon_meerschweinchen
    override val titleResource: Int = R.string.social_hamster_title
    override val descriptionResource: Int = R.string.social_hamster_desc
    override val explanationResource: Int = R.string.social_hamster_explanation
    override val hidden: Boolean = true
    override fun evaluate(log: ActionLog): Boolean = false
}