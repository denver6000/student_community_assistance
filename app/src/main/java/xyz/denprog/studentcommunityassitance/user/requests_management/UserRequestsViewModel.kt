package xyz.denprog.studentcommunityassitance.user.requests_management

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import xyz.denprog.studentcommunityassitance.database.dao.AppDao
import xyz.denprog.studentcommunityassitance.database.entity.Request
import javax.inject.Inject

@HiltViewModel
class UserRequestsViewModel @Inject constructor(
    private val appDao: AppDao
) : ViewModel() {

    fun getRequests(onFetchedRequests: (List<Request>) -> Unit) {
        viewModelScope.launch {
            onFetchedRequests(appDao.getAllRequests())
        }
    }
}
