package xyz.denprog.studentcommunityassitance.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Request(
    @PrimaryKey(autoGenerate = true)
    val requestId: Long = 0L,
    val title: String,
    val category: String,
    val description: String,
    val timestamp: Long,
    val adminNotes: String = "",
    val status: Int = 1
) : java.io.Serializable
