package com.example.jetpack_compose_login_app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserModelEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomUserModelDao(): UserModelDao
}

fun createRoomInstance(applicationContext: Context): AppDatabase {
    return Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "usermodel.db"
    ).allowMainThreadQueries()
        .build()
}


fun createRoomDao(appDatabase: AppDatabase): UserModelDao {
    return appDatabase.roomUserModelDao()
}
