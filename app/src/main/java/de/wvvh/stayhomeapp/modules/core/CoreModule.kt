package de.wvvh.stayhomeapp.modules.core

import de.wvvh.stayhomeapp.achievements.IAchievement
import de.wvvh.stayhomeapp.modules.IModule
import de.wvvh.stayhomeapp.quests.IQuestBuilder

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