package de.wvvh.stayhomeapp.achievements

import de.wvvh.stayhomeapp.actionLogging.ActionLog

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
abstract class Achievement {
    init {
        selfRegister()
    }

    abstract val hidden: Boolean
    abstract fun evaluate(log: ActionLog): Boolean
    private fun selfRegister() = AchievementStore.register(this)
}