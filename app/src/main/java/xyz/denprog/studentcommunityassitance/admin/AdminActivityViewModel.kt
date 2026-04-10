package xyz.denprog.studentcommunityassitance.admin

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
class AdminActivityViewModel @Inject constructor(
    val appDao: AppDao
) : ViewModel() {

    public var requestSelected: Long? = null

    fun loadRequest(requestId: Long, onRequestLoaded: (Request) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val request = appDao.getRequestById(requestId)
                val requestNonNull = request
                    ?: throw IllegalArgumentException("Request with ID $requestId not found")
                onRequestLoaded(requestNonNull)
            }
        }
    }

    fun update(requestId: Long, newStatus: Int, onUpdateComplete: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val request = appDao.getRequestById(requestId)
                if (request != null) {
                    val updatedRequest = request.copy(status = newStatus)
                    appDao.updateRequest(updatedRequest)
                    withContext(Dispatchers.Main) {
                        onUpdateComplete()
                    }
                } else {
                    // Handle the case where the request is not found
                }
            }
        }

    }
}