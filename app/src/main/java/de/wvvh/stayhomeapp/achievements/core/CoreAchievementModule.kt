package de.wvvh.stayhomeapp.achievements.core

import de.wvvh.stayhomeapp.achievements.IAchievement
import de.wvvh.stayhomeapp.achievements.IAchievementModule

/**
 *
 *
 * @author Antonius Naumann
 * @date 21.03.2020
 */
object CoreAchievementModule: IAchievementModule {
    override val achievements: List<IAchievement> = listOf(
        StayHome3Days,
        StayHome5Days,
        StayHome7Days
    )
}