package xyz.denprog.studentcommunityassitance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.denprog.studentcommunityassitance.database.dao.AppDao
import xyz.denprog.studentcommunityassitance.database.entity.User
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val appDao: AppDao
) : ViewModel() {

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

}