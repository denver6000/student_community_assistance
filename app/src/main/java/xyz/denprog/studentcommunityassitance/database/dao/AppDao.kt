package xyz.denprog.studentcommunityassitance.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import xyz.denprog.studentcommunityassitance.database.entity.Announcement
import xyz.denprog.studentcommunityassitance.database.entity.Request
import xyz.denprog.studentcommunityassitance.database.entity.User


@Dao
interface AppDao {
    @Insert
    fun insertAnnouncement(announcement: Announcement): Long

    @Delete
    fun deleteAnnouncement(announcement: Announcement)

    @Insert
    fun insertRequest(request: Request): Long

    @Update
    fun updateRequest(request: Request)

    @Query("SELECT * FROM `User` WHERE User.email = :username AND password = :password LIMIT 1")
    fun login(username: String, password: String): User?


    @Insert
    fun insertUser(user: User): Long

    @Query(
        "SELECT * FROM `User` WHERE User.email = :email LIMIT 1"
    )
    fun doesUserExist(email: String): User?
}