package de.wvvh.stayhomeapp.achievements.core

import de.wvvh.stayhomeapp.achievements.IAchievement
import de.wvvh.stayhomeapp.achievements.IAchievementModule
import de.wvvh.stayhomeapp.achievements.IModule
import de.wvvh.stayhomeapp.quests.IQuestBuilder
import de.wvvh.stayhomeapp.quests.IQuestModule

/**
 * Contains all achievements which are connected to basic functionality of the app.
 * The achievements mentioned here are closely connected to staying home.
 *
 * @author Antonius Naumann
 * @date 21.03.2020
 */
object CoreModule: IModule {
    override val achievements: List<IAchievement> = listOf(
        StayHome3Days,
        StayHome5Days,
        StayHome7Days
    )

    override val quests: List<IQuestBuilder> = listOf()
}