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
    private val _log = LogLoader.read()
    val log: ActionLog
        get() = _log

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
        _log.addObserver(this::notifyAchievements)
    }

    /**
     * Adds new Entry to ActionLog and stores it
     */
    fun addEntry(element: Entry) {
        _log.add(element)
        LogLoader.write(_log)
    }

    fun loadModule(module: IAchievementModule) = module.achievements.forEach { register(it)}
    fun register(element: IAchievement) = _achievements.add(element)

    /**
     * Check if Achievements are finished
     */
    public fun notifyAchievements(log: ActionLog = _log) {
        val finished = achievements.filter { it.evaluate(log) }
        if (!(finished unorderedEquals finishedAchievements)) {
            finishedAchievements = finished
        }
    }
}