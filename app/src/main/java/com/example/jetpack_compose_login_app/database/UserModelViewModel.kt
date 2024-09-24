package com.example.jetpack_compose_login_app.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class UserModelViewModel(private val dao: UserModelDao) : ViewModel() {

    // Function to add data (no changes)
    fun addData(
        fullName: String, email: String, password: String
    ) {
        viewModelScope.launch {
            val newDataToAdd = UserModel(
                fullName = fullName, email = email, password = password
            )
            dao.upsertUserModel(newDataToAdd)
        }
    }

    // Function to get all records (no changes)
    fun getAllRecords(): Flow<List<UserModel>> {
        return dao.getAllRecords()
    }

    // Function to get user by email - using a coroutine
    fun getUserByEmail(email: String, onResult: (UserModel?) -> Unit) {
        viewModelScope.launch {
            val user = dao.getUserByEmail(email)
            onResult(user)  // This will return the user result asynchronously
        }
    }
}
