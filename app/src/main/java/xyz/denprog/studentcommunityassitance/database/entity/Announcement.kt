package xyz.denprog.studentcommunityassitance.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Announcement (
    @PrimaryKey(autoGenerate = true)
    public var announcementId: Long,
    public var title: String,
    public var content: String,
    public var timestamp: Long
)