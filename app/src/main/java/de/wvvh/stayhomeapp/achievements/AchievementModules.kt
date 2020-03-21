package de.wvvh.stayhomeapp.achievements

import de.wvvh.stayhomeapp.achievements.core.CoreAchievementModule

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
object AchievementModules {
    val modules: List<IAchievementModule> = listOf(
        CoreAchievementModule
    )
}