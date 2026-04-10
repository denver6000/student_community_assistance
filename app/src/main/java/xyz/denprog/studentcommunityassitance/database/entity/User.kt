package xyz.denprog.studentcommunityassitance.database.entity

import androidx.room.Entity

@Entity
data class User(
    val userId: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val isAdmin: Boolean
)