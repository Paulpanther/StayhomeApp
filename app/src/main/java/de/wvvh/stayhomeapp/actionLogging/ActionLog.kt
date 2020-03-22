package de.wvvh.stayhomeapp.actionLogging

import android.util.Log
import java.util.*

data class Entry(val date: Date, val action: Action)

class Action(val actionString: String): CharSequence by actionString {
    constructor(tag: String, event: String): this(tag + event)
    override fun toString() = actionString
    override fun hashCode(): Int = actionString.hashCode()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Action
        if (actionString != other.actionString) return false
        return true
    }
}

/**
 * @author Antonius Naumann
 * @author Julian Benda
 * @date 21.03.2020
 */
class ActionLog(private val log: MutableList<Entry> = mutableListOf()): List<Entry> by log {
    private val observers: MutableList<(ActionLog) -> Unit> = mutableListOf()

    companion object {
        fun buildAction(vararg tags: String): String {
            return tags.joinToString(":")
        }
    }

    fun add(element: Entry) {
        log.add(element)
        observers.forEach{ it(this) }

        // Log.d("ACTION_LOG", element.action.toString())
    }

    fun addObserver(observer: (ActionLog) -> Unit) {
        observers.add(observer)
    }
}