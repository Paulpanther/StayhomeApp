package de.wvvh.stayhomeapp.modules

import de.wvvh.stayhomeapp.achievements.AchievementStore
import de.wvvh.stayhomeapp.quests.QuestManager

/**
 * @author Antonius Naumann
 * @date 22.03.2020
 */
object ModuleLoader {
    fun loadModules(modules: List<IModule>) = modules.forEach( this::loadModule)
    private fun loadModule(module: IModule) {
        AchievementStore.loadModule(module)
        QuestManager.loadModule(module)
    }
}