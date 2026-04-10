package xyz.denprog.studentcommunityassitance.admin.ui.announcement_management

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import xyz.denprog.studentcommunityassitance.database.dao.AppDao
import xyz.denprog.studentcommunityassitance.database.entity.Announcement
import javax.inject.Inject

@HiltViewModel class AnnouncementsManagementViewModel @Inject constructor(
    appDao: AppDao
) : ViewModel(){

    val announcements: MutableLiveData<List<Announcement>> = MutableLiveData()
    init {
        viewModelScope.launch {
            announcements.value = appDao.getAllAnnouncements()
        }
    }

}