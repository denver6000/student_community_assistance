package xyz.denprog.studentcommunityassitance

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.denprog.studentcommunityassitance.database.dao.AppDao
import xyz.denprog.studentcommunityassitance.database.entity.Feedback
import xyz.denprog.studentcommunityassitance.database.entity.User
import xyz.denprog.studentcommunityassitance.user.feedback.add.SetFeedbackFragment
import xyz.denprog.studentcommunityassitance.utils.toEpochMillis
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.math.PI
import kotlin.math.log

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val appDao: AppDao
) : ViewModel() {

    var selectedRequestId: Long? = null

    var selectedRatingIndex: MutableLiveData<Int?> = MutableLiveData(null)
    fun setFeedBackRequestId(content: String, onError: (String) -> Unit, onSuccess: () -> Unit) {
        val epoch = LocalDateTime.now().toEpochMillis()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val loggedInUser =  appDao.getLoggedInUser()?.userId
                if (selectedRatingIndex.value != null && selectedRequestId != null && loggedInUser != null) {
                    val feedback = Feedback(
                        feedbackId = epoch,
                        userId = loggedInUser,
                        requestId = selectedRequestId!!,
                        content = content,
                        rating = SetFeedbackFragment.RATINGS[selectedRatingIndex.value!!],
                        timestamp = epoch
                    )
                    appDao.insertFeedBack(feedback)
                    appDao.updateRequest(
                        appDao.getRequestById(selectedRequestId!!)!!.copy(
                            hasFeedBack = true
                        )
                    )
                    onSuccess()
                } else {
                    onError("Please make sure you have selected a rating and a request to give feedback on.")
                }
            }
        }
    }


    fun insertDefaultCreds() {

        viewModelScope.launch {

             withContext(Dispatchers.IO) {
                 val user = appDao.insertUser(User(
                     firstName = "Admin",
                     lastName = "Admin",
                     email = "admin",
                     password = "admin",
                     userId = 1,
                     isAdmin = true
                 ))

                 val user2 = appDao.insertUser(User(
                     firstName = "User",
                     lastName = "User",
                     email = "user",
                     password = "user",
                     userId = 2,
                     isAdmin = false
                 ))
             }

        }

    }

    fun checkIfUsersExists(
        onExist: () -> Unit,
        onNotExist: () -> Unit
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val user = appDao.doesUserExist("admin")
                if (user != null) {
                    onExist()
                } else {
                    onNotExist()
                }
            }
        }
    }

    fun logout(onLoggedOut: () -> Unit) {
        viewModelScope.launch {
            appDao.clearLoggedInUser()
            onLoggedOut()
        }
    }

}
