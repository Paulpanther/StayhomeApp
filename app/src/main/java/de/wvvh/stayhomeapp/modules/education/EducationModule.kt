package de.wvvh.stayhomeapp.modules.education

import de.wvvh.stayhomeapp.achievements.IAchievement
import de.wvvh.stayhomeapp.modules.IModule
import de.wvvh.stayhomeapp.quests.IQuestBuilder

/**
 * @author Antonius Naumann
 * @date 22.03.2020
 */
object EducationModule: IModule {
    override val quests: List<IQuestBuilder> = listOf(
        BookwormQuestBuilder
    )
    override val achievements: List<IAchievement> = listOf(
        GeniusAchievement
    )
}