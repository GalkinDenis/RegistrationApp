package com.example.app.domain.repository

import com.example.app.domain.LogInRequest
import com.example.app.domain.RegistryRequest

interface AuthorizationRepository {
    suspend fun saveUser(users: String, password: String) : RegistryRequest
    suspend fun getUser(userEmail: String, password: String) : LogInRequest
}