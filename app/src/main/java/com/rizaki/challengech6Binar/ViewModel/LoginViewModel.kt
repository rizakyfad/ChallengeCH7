package com.rizaki.challengech6Binar.ViewModel

import android.util.Log
import androidx.lifecycle.*
import com.rizaki.challengech6Binar.database.local.User
import com.rizaki.challengech6Binar.database.local.UserRepository
import com.rizaki.challengech6Binar.helper.Event
import com.rizaki.challengech6Binar.helper.UserDataStoreManager
import kotlinx.coroutines.launch


class LoginViewModel(
    private val repository: UserRepository,
    private val pref: UserDataStoreManager
) : ViewModel() {

    private var _checkUser = MutableLiveData<User>()
    val checkUser: LiveData<User> = _checkUser

    private var _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = _message

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val data = repository.checkUser(email, password)
                _checkUser.value = data
                @Suppress("SENSELESS_COMPARISON")
                if (email.isEmpty() || password.isEmpty()) {
                    _message.value = Event("Field cannot be empty")
                } else if (data == null) {
                    _message.value = Event("User does not exist")
                }

            } catch (e: Exception) {
                Log.d("LoginViewModel", "${e.message}")
            }
        }
    }

    fun saveUserDataStore(id: Int, status: Boolean) {
        viewModelScope.launch {
            pref.saveUser(id, status)
        }
    }

    fun getLoginStatus(): LiveData<Boolean> {
        return pref.getLoginStatus().asLiveData()
    }
}
