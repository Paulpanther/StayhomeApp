package de.wvvh.stayhomeapp.actionLogging

import de.wvvh.stayhomeapp.util.Storage
import io.paperdb.Paper

object LogLoader {

    fun read(): ActionLog {
        val list = Paper.book().read<MutableList<Entry>>(Storage.ACTION_LOG, mutableListOf())
        return ActionLog(list)
    }

    fun write(log: ActionLog) {
        Paper.book().write(Storage.ACTION_LOG, log.toMutableList())
    }
}