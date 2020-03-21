package de.wvvh.stayhomeapp.wifi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Connection(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "time") val time: Long,
    @ColumnInfo(name = "network_id") val networkId: Int,
    @ColumnInfo(name = "answered_question") var answeredQuestion: Boolean = false)
