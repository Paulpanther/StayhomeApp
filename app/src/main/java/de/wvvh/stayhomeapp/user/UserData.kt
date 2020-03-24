package de.wvvh.stayhomeapp.user

import android.util.Log
import de.wvvh.stayhomeapp.R
import de.wvvh.stayhomeapp.achievements.AchievementStore
import de.wvvh.stayhomeapp.util.Storage
import io.paperdb.Paper
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

interface IUserDataUpdate: IUserData {
    fun register(observer: (IUserData) -> Unit)
}

interface IUserData {
    val level: Int
    var xp: Float
    var icon: Int
    var name: String
}

object UserDataStore: IUserDataUpdate {
    private val observers: MutableList<(IUserData) -> Unit> = mutableListOf()
    private val noUser = UserData("___No User", 0f, 0)
    private var user: UserData = noUser
        get() {
            if (field == noUser) {
                val loaded = UserData.load()
                user = loaded ?: UserData()
            }
            return field
        }

    override var xp: Float
        get() = user.xp
        set(value) {
            user.xp = value
            notifyObservers(this)
        }
    override var name: String
        get() = user.name
        set(value) {
            user.name = value
            notifyObservers(this)
        }
    override var icon: Int
        get() = user.icon
        set(value) {
            user.icon = value
            notifyObservers(this)
        }
    override val level: Int
        get() = user.level


    override fun register(observer: (IUserData) -> Unit) {
        observers.add(observer)
    }

    private fun onUserDataChange(property: KProperty<*>, oldValue: UserData, newValue: UserData) {
        notifyObservers(this)
    }

    private fun notifyObservers(newValue: IUserData) {
        observers.forEach{ it(newValue) }
    }
}

data class UserData(
    private var _name: String = "",
    private var _xp: Float = 0f,
    private var _icon: Int = R.drawable.ic_icon_user_2) {

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

    var name: String
        get() = _name
        set(value) {
            _name = value
            store()
        }

    private val xpPerLevel = 1000f
    val level
        get() = (xp / xpPerLevel).toInt()

    private fun store() {
        Paper.book().write(Storage.USER_DATA, this)
    }
}