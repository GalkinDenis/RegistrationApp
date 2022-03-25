package com.example.app.data.datasource

import com.example.app.domain.LogInRequest
import com.example.app.domain.RegistryRequest

interface UsersDataSource {
    suspend fun saveUser(userEmail: String, password: String) : RegistryRequest
    suspend fun getUser(userEmail: String, password: String) : LogInRequest
}