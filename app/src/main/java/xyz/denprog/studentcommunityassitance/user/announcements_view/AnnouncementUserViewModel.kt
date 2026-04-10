package xyz.denprog.studentcommunityassitance.user.announcements_view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import xyz.denprog.studentcommunityassitance.database.dao.AppDao
import xyz.denprog.studentcommunityassitance.database.entity.Announcement
import javax.inject.Inject

@HiltViewModel
class AnnouncementUserViewModel @Inject constructor(val appDao: AppDao) : ViewModel()
{


    fun getAnnouncements(onFetchedAnnouncement: (List<Announcement>) -> Unit) {
        viewModelScope.launch {
            val announcements = appDao.getAllAnnouncements()
            onFetchedAnnouncement(announcements)
        }
    }


}