package de.wvvh.stayhomeapp.actionLogging

import java.util.*

data class Entry(val date: Date, val action: Action)

class Action(val actionString: String): CharSequence by actionString {
    constructor(tag: String, event: String): this(tag + event)
    override fun toString() = actionString
    override fun hashCode(): Int = actionString.hashCode()
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
    }

    fun addObserver(observer: (ActionLog) -> Unit) {
        observers.add(observer)
    }
}