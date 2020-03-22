package de.wvvh.stayhomeapp.modules

import de.wvvh.stayhomeapp.achievements.IAchievementModule
import de.wvvh.stayhomeapp.modules.core.CoreModule
import de.wvvh.stayhomeapp.quests.IQuestModule

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
object Modules: List<IModule> by listOf(
    CoreModule
)

interface IModule: IQuestModule,
    IAchievementModule