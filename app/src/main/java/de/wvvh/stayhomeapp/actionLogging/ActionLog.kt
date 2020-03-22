package de.wvvh.stayhomeapp.actionLogging

import java.util.*

data class Entry(val date: Date, val action: Action)

/**
 * An Action done by the User like finishing quests or going outside
 */
class Action(val actionString: String): CharSequence by actionString {
    constructor(tag: String, event: String): this("$tag:$event")

    fun getTag(): String {
        return actionString.split(":").first()
    }

    fun getEvent(): String {
        return actionString.split(":").last()
    }

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
 * Stores all Actions done by the user
 *
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