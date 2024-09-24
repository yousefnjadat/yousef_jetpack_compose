package com.example.jetpack_compose_login_app.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserModel::class], version = 1)
abstract class UserModelDatabase : RoomDatabase() {
    abstract val dao: UserModelDao
}