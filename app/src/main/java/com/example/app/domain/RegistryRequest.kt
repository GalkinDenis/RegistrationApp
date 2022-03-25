package com.example.app.domain

sealed class RegistryRequest {
    object UserSaved : RegistryRequest()
    object AlreadyExist : RegistryRequest()
    object Pending : RegistryRequest()
}