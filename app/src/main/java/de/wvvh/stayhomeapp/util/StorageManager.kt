package de.wvvh.stayhomeapp.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.wvvh.stayhomeapp.wifi.Connection


class StorageManager(private val c: Context) {

    companion object {
        private const val KEY_SHARED_PREFERENCES = "key_shared_preferences_stay_home"
    }

    val connections: ListPreference<Connection>
    val homeWifi: Preference<Int>

    init {
        connections = ListPreference(c, "key_wifi_connections", mutableListOf())
        homeWifi = Preference(c, "key_home_wifi", -1)
    }

    inner class ListPreference<T>(
        private val c: Context,
        private val key: String,
        private val defaultValue: MutableList<T>
    ) : Preference<MutableList<T>>(c, key, defaultValue) {

        fun add(value: T) {
            set(get().apply {
                add(value)
            })
        }
    }

    open inner class Preference<T>(
        private val c: Context,
        private val key: String,
        private val defaultValue: T
    ) {

        fun get(): T {
            val str = getPreferences().getString(key, "")
            return if (str != null && str != "") {
                fromJson(str)
            } else {
                defaultValue
            }
        }

        fun set(value: T) {
            val str = toJson(value)
            getPreferences().edit().putString(key, str).apply()
        }

        private fun getPreferences(): SharedPreferences {
            return c.getSharedPreferences(KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }

        private fun <T> fromJson(json: String): T {
            return Gson().fromJson(json, object: TypeToken<T>(){}.type)
        }

        private fun <T> toJson(src: T): String {
            return Gson().toJson(src, object: TypeToken<T>(){}.type)
        }
    }
}


