package de.wvvh.stayhomeapp.modules

import de.wvvh.stayhomeapp.achievements.IAchievementModule
import de.wvvh.stayhomeapp.modules.core.CoreModule
import de.wvvh.stayhomeapp.modules.core.DebugModule
import de.wvvh.stayhomeapp.modules.education.EducationModule
import de.wvvh.stayhomeapp.modules.health.HealthModule
import de.wvvh.stayhomeapp.modules.selfcare.SelfcareModule
import de.wvvh.stayhomeapp.modules.social.SocialModule
import de.wvvh.stayhomeapp.modules.work.WorkModule
import de.wvvh.stayhomeapp.quests.IQuestModule

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
object Modules: List<IModule> by listOf(
    DebugModule,
    CoreModule,
    EducationModule,
    HealthModule,
    SelfcareModule,
    SocialModule,
    WorkModule
)

interface IModule: IQuestModule, IAchievementModule