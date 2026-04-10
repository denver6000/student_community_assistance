package xyz.denprog.studentcommunityassitance.admin.ui.requests_management

import androidx.lifecycle.MutableLiveData
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
class RequestsViewModel @Inject constructor(
    val appDao: AppDao
) : ViewModel() {

    fun getAllRequests(onSuccess: (List<Request>) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val requests = appDao.getAllRequests()
                withContext(Dispatchers.Main) {
                    onSuccess(requests)
                }
            }
        }
    }

}
