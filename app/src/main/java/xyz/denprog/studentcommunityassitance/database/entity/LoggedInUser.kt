package xyz.denprog.studentcommunityassitance.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logged_in_user")
data class LoggedInUser(
    @PrimaryKey
    val sessionId: Int = 1,
    val userId: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val isAdmin: Boolean
)
