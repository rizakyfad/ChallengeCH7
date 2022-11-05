package com.rizaki.challengech6Binar.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizaki.challengech6Binar.database.local.User
import com.rizaki.challengech6Binar.database.local.UserRepository
import com.rizaki.challengech6Binar.helper.Event
import kotlinx.coroutines.launch


class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = _message

    fun insert(username: String, email: String, password: String, confirmPassword: String) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            _message.value = Event("Field cannot be empty")
        } else if (password != confirmPassword) {
            _message.value = Event("Password mismatch")
        } else {
            val user = User(username = username, email = email, password = password)
            viewModelScope.launch {
                repository.insert(user)
            }
            _message.value = Event("Register successfully")
            _success.value = true
        }
    }
}