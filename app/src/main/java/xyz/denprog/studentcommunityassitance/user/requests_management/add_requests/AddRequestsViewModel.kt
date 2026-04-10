package xyz.denprog.studentcommunityassitance.user.requests_management.add_requests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.denprog.studentcommunityassitance.constants.ProjectConstants
import xyz.denprog.studentcommunityassitance.database.dao.AppDao
import xyz.denprog.studentcommunityassitance.database.entity.Request
import xyz.denprog.studentcommunityassitance.utils.toEpochMillis
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddRequestsViewModel @Inject constructor(
    private val appDao: AppDao
) : ViewModel() {

    fun addRequest(
        title: String,
        category: String,
        description: String,
        onSuccess: () -> Unit = {}
    ) {
        val request = Request(
            title = title,
            category = category,
            description = description,
            timestamp = LocalDateTime.now().toEpochMillis(),
            adminNotes = "",
            status = ProjectConstants.PENDING
        )

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                appDao.insertRequest(request)
            }
            onSuccess()
        }
    }
}
