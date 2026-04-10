package xyz.denprog.studentcommunityassitance.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Request (
    @PrimaryKey
    val requestId: Long,
    val title: String,
    val category: String,
    val description: String,
    val timestamp: Long,
    val adminNotes: String
)