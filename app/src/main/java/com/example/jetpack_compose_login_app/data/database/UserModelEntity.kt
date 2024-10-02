package com.example.jetpack_compose_login_app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// Adding @Entity here tells Room that this will be a table in our database
@Entity
data class UserModelEntity(
    // We can add attributes to the fields of our database using the @PrimaryKey annotation
    // the PrimaryKey annotation here tells our database that this column the primary key
    // and is therefore guaranteed to be unique
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fullName: String,
    val email: String,
    val password: String
)
