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
    private val _log = LogLoader.read()
    val log: List<Entry>
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

    init {
        _log.addObserver(this::notifyAchievements)
    }

    fun addEntry(element: Entry) {
        _log.add(element)
        LogLoader.write(_log)
    }

    // TODO: save if achievements are completed (progress)
    // TODO: save progress to file system and implement logic to reload progress
    // TODO: how to map objects to file system? (reflection, id system whatever)

    fun loadModule(module: IAchievementModule) = module.achievements.forEach { register(it)}
    fun register(element: IAchievement) = _achievements.add(element)

    public fun notifyAchievements(log: ActionLog = _log) {
        val finished = achievements.filter { it.evaluate(log) }
        if (!(finished unorderedEquals finishedAchievements)) {
            finishedAchievements = finished
        }
    }
}