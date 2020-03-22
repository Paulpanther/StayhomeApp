package de.wvvh.stayhomeapp.actionLogging

abstract class Filter(val log: List<Entry>) {
    abstract fun filter(value: String): List<Entry>
}

class TagFilter(log: List<Entry>): Filter(log) {
    override fun filter(value: String) = log.filter {
        val elements = it.action.split(":")
        elements.isNotEmpty() && elements.first() == value
    }
}