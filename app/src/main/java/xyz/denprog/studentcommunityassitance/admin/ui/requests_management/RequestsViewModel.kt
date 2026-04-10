package xyz.denprog.studentcommunityassitance.admin.ui.requests_management

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import xyz.denprog.studentcommunityassitance.database.dao.AppDao
import xyz.denprog.studentcommunityassitance.database.entity.Request
import javax.inject.Inject

@HiltViewModel
class RequestsViewModel @Inject constructor(
    appDao: AppDao
) : ViewModel() {

    val requestsMutableLiveData = MutableLiveData<List<Request>>()

    init {
        viewModelScope.launch {
            requestsMutableLiveData.value = appDao.getAllRequests()
        }
    }

}