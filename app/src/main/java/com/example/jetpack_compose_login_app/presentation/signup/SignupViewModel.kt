package com.example.jetpack_compose_login_app.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_login_app.data.database.UserModelEntity
import com.example.jetpack_compose_login_app.domain.login.LoginRepository
import com.example.jetpack_compose_login_app.domain.signup.SignupRepository
import kotlinx.coroutines.launch

class SignupViewModel(
    private val signupRepository: SignupRepository
) : ViewModel() {

    fun signup(
        fullName: String, email: String, password: String
    ) {
        viewModelScope.launch {
            val newDataToAdd = UserModelEntity(
                fullName = fullName, email = email, password = password
            )
            signupRepository.upsertUserModel(newDataToAdd)
        }
    }

}
