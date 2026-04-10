package xyz.denprog.studentcommunityassitance.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val userId: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val isAdmin: Boolean
)