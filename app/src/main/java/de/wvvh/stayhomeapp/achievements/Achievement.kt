package de.wvvh.stayhomeapp.achievements

import de.wvvh.stayhomeapp.actionLogging.ActionLog

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
interface IAchievement {
    val imageResource: Int
    val titleResource: Int
    val descriptionResource: Int
    val explanationResource: Int

    /**
     * marks an achievement as hidden, so its requirements, title or image are not shown to the user
     */
    val hidden: Boolean

    /**
     * evaluates if the achievements requirements are met
     */
    fun evaluate(log: ActionLog): Boolean
}

/**
 * Module description for achievement modules. Used to load all achievements of a specific module
 *
 * @author Antonius Naumann
 * @date 21.03.2020
 */
interface IAchievementModule {
    val achievements: List<IAchievement>
}