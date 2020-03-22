package de.wvvh.stayhomeapp.user

import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.AchievementStore
import de.wvvh.stayhomeapp.util.Storage
import io.paperdb.Paper

object UserDataStore {
    private lateinit var _user: UserData
    val user: UserData
        get() {
            if (!::_user.isInitialized) {
                val loaded = UserData.load()
                if (loaded == null) {
                    return UserData("No User")
                } else {
                    _user = loaded
                }
            }
            return _user
        }

    fun createUser(name: String) {
        if (!::_user.isInitialized) {
            _user = UserData(name)
        }
    }
}

data class UserData(
    val name: String,
    private var _xp: Float = 0f,
    private var _icon: Int = R.drawable.ic_icon_stubenhocker_3) {

    companion object {
        fun load(): UserData? {
            return Paper.book().read(Storage.USER_DATA)
        }

        fun getAvailableIcons(): List<Int> {
            return AchievementStore.finishedAchievements.map { it.imageResource }
        }
    }

    var xp: Float
        get() = _xp
        set(value) {
            _xp = value
            store()
        }

    var icon: Int
        get() = _icon
        set(value) {
            _icon = value
            store()
        }

    init {
        store()
    }

    val level
        get() = xp.toInt()

    private fun store() {
        Paper.book().write(Storage.USER_DATA, this)
    }
}