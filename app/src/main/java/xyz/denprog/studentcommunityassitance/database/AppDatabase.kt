package xyz.denprog.studentcommunityassitance.database

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.denprog.studentcommunityassitance.database.dao.AppDao
import xyz.denprog.studentcommunityassitance.database.entity.Announcement
import xyz.denprog.studentcommunityassitance.database.entity.Feedback
import xyz.denprog.studentcommunityassitance.database.entity.Request
import xyz.denprog.studentcommunityassitance.database.entity.User

@Database(
    version = 1,
    entities = [
        User::class,
        Request::class,
        Feedback::class,
        Announcement::class
    ]
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun provideAppDao(): AppDao
}