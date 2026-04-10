package xyz.denprog.studentcommunityassitance.admin.ui.announcement_management

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.denprog.studentcommunityassitance.database.dao.AppDao
import javax.inject.Inject

@HiltViewModel class AnnouncementsManagementViewModel @Inject constructor(
    val appDao: AppDao
) : ViewModel(){

    val announcements: MutableLiveData<List<AnnouncementView>> = MutableLiveData(emptyList())

    init {
        getAllAnnouncements()
    }

    fun getAllAnnouncements(onSuccess: (List<AnnouncementView>) -> Unit = {}) {
        viewModelScope.launch {
            val announcementViews = withContext(Dispatchers.IO) {
                appDao.getAllAnnouncements()
                    .map(AnnouncementView::from)
                    .sortedByDescending { it.publishedAt }
            }

            announcements.value = announcementViews
            onSuccess(announcementViews)
        }
    }

}
