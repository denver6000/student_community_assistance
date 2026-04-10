package xyz.denprog.studentcommunityassitance.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import xyz.denprog.studentcommunityassitance.database.entity.Announcement
import xyz.denprog.studentcommunityassitance.database.entity.Feedback
import xyz.denprog.studentcommunityassitance.database.entity.LoggedInUser
import xyz.denprog.studentcommunityassitance.database.entity.Request
import xyz.denprog.studentcommunityassitance.database.entity.User


@Dao
interface AppDao {
    @Insert
    fun insertAnnouncement(announcement: Announcement): Long

    @Delete
    fun deleteAnnouncement(announcement: Announcement)

    @Query("SELECT * FROM Announcement ORDER BY timestamp DESC")
    fun getAllAnnouncements(): List<Announcement>
    @Insert
    suspend fun insertRequest(request: Request): Long

    @Update
    fun updateRequest(request: Request)

    @Query("UPDATE `Request` SET hasFeedBack = :hasFeedBack WHERE requestId = :requestId")
    fun updateRequestFeedBackStatusById(requestId: Long, hasFeedBack: Boolean);

    @Query("SELECT * FROM `User` WHERE User.email = :username AND password = :password LIMIT 1")
    suspend fun login(username: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLoggedInUser(loggedInUser: LoggedInUser)

    @Query("SELECT * FROM logged_in_user LIMIT 1")
    suspend fun getLoggedInUser(): LoggedInUser?

    @Query("DELETE FROM logged_in_user")
    suspend fun clearLoggedInUser()

    @Insert
    suspend fun insertUser(user: User): Long

    @Query(
        "SELECT * FROM `User` WHERE User.email = :email LIMIT 1"
    )
    suspend fun doesUserExist(email: String): User?

    @Query("SELECT * FROM `Request` ORDER BY timestamp DESC")
    suspend fun getAllRequests(): List<Request>

    @Query("SELECT * FROM `Request` WHERE requestId = :requestId LIMIT 1")
    suspend fun getRequestById(requestId: Long): Request?

    @Insert
    fun insertFeedBack(feedback: Feedback);

}
