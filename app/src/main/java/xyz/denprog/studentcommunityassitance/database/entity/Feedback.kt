package xyz.denprog.studentcommunityassitance.database.entity

import androidx.room.Entity

@Entity
data class Feedback (
    val feedbackId: Long,
    val userId: Long,
    val requestId: Long,
    val content: String,
    val rating: Int,
    val timestamp: Long
)