package com.example.app.data.datasource.localdatasource

import com.example.app.domain.entities.ChangePasswordRequest
import com.example.app.domain.entities.LogInRequest
import com.example.app.domain.entities.UserRegistrationRequest

interface LocalDataSource {
    suspend fun registrationUser(userEmail: String, password: String) : UserRegistrationRequest
    suspend fun getUser(userEmail: String, password: String) : LogInRequest
    suspend fun changePassword(userEmail: String, oldPassword: String, newPassword: String) : ChangePasswordRequest
}