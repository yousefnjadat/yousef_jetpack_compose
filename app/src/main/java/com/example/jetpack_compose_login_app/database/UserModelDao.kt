package com.example.jetpack_compose_login_app.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Upsert
import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow


//Data Access Object
@Dao
interface UserModelDao {
    // Upsert means that this will either insert the object if it doesn't exist in the database
    // or update the object if it does already exists in the database
    @Upsert
    suspend fun upsertUserModel(userModel: UserModel)

    @Query("SELECT * FROM userModel")
    fun getAllRecords(): Flow<List<UserModel>>

    @Query("SELECT * FROM UserModel WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserModel?
}