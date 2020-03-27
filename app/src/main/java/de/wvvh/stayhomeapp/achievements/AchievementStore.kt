package de.wvvh.stayhomeapp.achievements
import de.wvvh.stayhomeapp.actionLogging.ActionLog
import de.wvvh.stayhomeapp.actionLogging.Entry
import de.wvvh.stayhomeapp.actionLogging.LogLoader
import de.wvvh.stayhomeapp.util.Storage
import de.wvvh.stayhomeapp.util.unorderedEquals
import io.paperdb.Paper

/**
 * @author Antonius Naumann
 * @date 21.03.2020
 */
object AchievementStore {

    /**
     * The ActionLog which stores all Actions done by the user
     */
    val log: ActionLog = LogLoader.read()

    private val _achievements: MutableList<IAchievement> = mutableListOf()
    val achievements: List<IAchievement>
        get() = _achievements

    private lateinit var _finishedAchievements: List<IAchievement>
    var finishedAchievements: List<IAchievement> = listOf()
        get() {
            if (!::_finishedAchievements.isInitialized) {
                _finishedAchievements = FinishedAchievementLoader.fromAll(_achievements)
            }
            return _finishedAchievements
        }
        private set(value) {
            FinishedAchievementLoader.write(value)
            field = value
        }

    fun isAchievementFinished(achievement: IAchievement) = finishedAchievements.contains(achievement)

    init {
        log.addObserver(this::_notifyAchievements)
    }

    /**
     * Adds new Entry to ActionLog and stores it
     */
    fun addEntry(element: Entry) {
        log.add(element)
        LogLoader.write(log)
    }

    fun loadModule(module: IAchievementModule) = module.achievements.forEach { register(it)}
    fun register(element: IAchievement) = _achievements.add(element)

    /**
     * Check if Achievements are finished
     */
    fun notifyAchievements(actionLog: ActionLog = log): Boolean {
        val finished = achievements.filter { it.evaluate(actionLog) }
        if (!(finished unorderedEquals finishedAchievements)) {
            finishedAchievements = finished
        }
        return finished.isNotEmpty()
    }

    private fun _notifyAchievements(actionLog: ActionLog = log) {
        notifyAchievements(log)
    }
}