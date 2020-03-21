package de.wvvh.stayhomeapp.achievements
import de.wvvh.stayhomeapp.actionLogging.ActionLog

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
object AchievementStore {
    private val achievements: MutableList<Achievement> = mutableListOf()
    private val log = ActionLog() // TODO: reload from persistent memory instead
    init {
        log.addObserver(this::notifyAchievements)
    }

    fun register(element: Achievement) = achievements.add(element)
    private fun notifyAchievements(log: ActionLog) = achievements.forEach { it.evaluate(log) }
}