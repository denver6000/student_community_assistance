package xyz.denprog.studentcommunityassitance.user.requests_management

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.denprog.studentcommunityassitance.database.dao.AppDao
import xyz.denprog.studentcommunityassitance.database.entity.Request
import javax.inject.Inject

@HiltViewModel
class UserViewRequestsViewModel @Inject constructor(private val appDao: AppDao) : ViewModel() {

    fun getRequests(onFetched: (List<Request>) -> Unit) {
        viewModelScope.launch {
            val requests = withContext(Dispatchers.IO) { appDao.getAllRequests() }
            onFetched(requests)
        }
    }
}
