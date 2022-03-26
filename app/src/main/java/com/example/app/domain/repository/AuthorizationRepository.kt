package com.example.app.domain.repository

import com.example.app.domain.entities.ChangePasswordRequest
import com.example.app.domain.entities.LogInRequest
import com.example.app.domain.entities.UserRegistrationRequest

interface AuthorizationRepository {
    suspend fun registrationUser(userEmail: String, password: String) : UserRegistrationRequest
    suspend fun getUser(userEmail: String, password: String) : LogInRequest
    suspend fun changePassword(userEmail: String, password: String, newPassword: String) : ChangePasswordRequest
}