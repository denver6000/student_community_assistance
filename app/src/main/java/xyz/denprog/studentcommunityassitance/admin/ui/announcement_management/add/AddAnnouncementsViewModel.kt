package xyz.denprog.studentcommunityassitance.admin.ui.announcement_management.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.denprog.studentcommunityassitance.database.dao.AppDao
import xyz.denprog.studentcommunityassitance.utils.toEpochMillis
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddAnnouncementsViewModel @Inject constructor(
    val appDao: AppDao
): ViewModel() {

    fun addAnnouncement(title: String, content: String, onSuccess: () -> Unit = {}) {
        val timestamp = LocalDateTime.now().toEpochMillis()
        val announcement = xyz.denprog.studentcommunityassitance.database.entity.Announcement(
            title = title,
            content = content,
            timestamp = timestamp
        )
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                appDao.insertAnnouncement(announcement)
            }
            onSuccess()
        }
    }

}
