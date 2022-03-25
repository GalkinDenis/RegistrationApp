package com.example.app.data.datasource.localdatasource

import com.example.app.domain.LogInRequest
import com.example.app.domain.RegistryRequest

interface LocalDataSource {
    suspend fun saveUsers(userEmail: String, password: String) : RegistryRequest
    suspend fun getUser(userEmail: String, password: String) : LogInRequest
}