package com.example.jetpack_compose_login_app.domain.signup

import com.example.jetpack_compose_login_app.data.database.UserModelDao
import com.example.jetpack_compose_login_app.data.database.UserModelEntity

class SignupRepository(
    private val userModelDao: UserModelDao
) {

    suspend fun upsertUserModel(userModelEntity: UserModelEntity) {
        return userModelDao.upsertUserModel(userModelEntity)
    }

}