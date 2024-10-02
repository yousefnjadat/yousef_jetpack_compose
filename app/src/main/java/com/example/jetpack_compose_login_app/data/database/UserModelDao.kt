package com.example.jetpack_compose_login_app.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


//Data Access Object
@Dao
interface UserModelDao {
    // Upsert means that this will either insert the object if it doesn't exist in the database
    // or update the object if it does already exists in the database
    @Upsert
    suspend fun upsertUserModel(userModelEntity: UserModelEntity)

    @Query("SELECT * FROM UserModelEntity WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserModelEntity?
}