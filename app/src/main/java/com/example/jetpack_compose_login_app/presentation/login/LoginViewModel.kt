package com.example.jetpack_compose_login_app.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_login_app.data.database.UserModelEntity
import com.example.jetpack_compose_login_app.domain.login.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {


    fun login(
        email: String,
        onResult: (UserModelEntity?) -> Unit
    ) {
        viewModelScope.launch {
            val user = loginRepository.getUserByEmail(email)
            onResult(user)
        }
    }
}
