package com.example.jetpack_compose_login_app.domain.login

import com.example.jetpack_compose_login_app.data.database.UserModelDao
import com.example.jetpack_compose_login_app.data.database.UserModelEntity

class LoginRepository(
    private val userModelDao: UserModelDao
) {

    suspend fun getUserByEmail(email: String): UserModelEntity? {
        return userModelDao.getUserByEmail(email)
    }

}