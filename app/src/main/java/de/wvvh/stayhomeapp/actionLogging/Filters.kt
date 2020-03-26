package de.wvvh.stayhomeapp.actionLogging

/**
 * Filters Entries with some properties
 */
abstract class Filter(val log: List<Entry>) {
    abstract fun filter(value: String): List<Entry>
    fun filter(value: Any?) = filter(value.toString())
}

class TagFilter(log: List<Entry>): Filter(log) {
    override fun filter(value: String) = log.filter {
        val elements = it.action.split(":")
        elements.isNotEmpty() && elements.first() == value
    }
}

class KeywordFilter(log: List<Entry>): Filter(log) {
    override fun filter(value: String): List<Entry> {
        return log.filter { it.action.contains(value) }
    }
}