package de.wvvh.stayhomeapp.modules.core

import de.wvvh.stayhomeapp.achievements.IAchievement
import de.wvvh.stayhomeapp.modules.IModule
import de.wvvh.stayhomeapp.modules.debug.DebugBackflipQuestBuilder
import de.wvvh.stayhomeapp.quests.IQuestBuilder

/**
 * Contains all achievements which are connected to basic functionality of the app.
 * The achievements mentioned here are closely connected to staying home.
 *
 * @author Antonius Naumann
 * @date 21.03.2020
 */
object DebugModule: IModule {
    override val achievements: List<IAchievement> = listOf(

    )

    override val quests: List<IQuestBuilder> = listOf(
        DebugBackflipQuestBuilder
    )
}