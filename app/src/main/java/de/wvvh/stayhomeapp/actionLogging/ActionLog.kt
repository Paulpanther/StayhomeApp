package de.wvvh.stayhomeapp.actionLogging

import java.util.*

enum class Action {
    LEFT_HOME,
}

data class Entry(val date: Date, val action: Action)

/**
 * @author Antonius Naumann
 * @author Julian Benda
 * @date 21.03.2020
 */
class ActionLog(private val log: MutableList<Entry> = mutableListOf()): List<Entry> by log {
    private val observers: MutableList<(ActionLog) -> Unit> = mutableListOf();

    fun add(element: Entry) {
        log.add(element)
        observers.forEach{ it(this) }
    }

    fun addObserver(observer: (ActionLog) -> Unit) {
        observers.add(observer)
    }
}