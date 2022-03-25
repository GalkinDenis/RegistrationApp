package com.example.app.domain

sealed class RegistryRequest {
    object UserSaved : RegistryRequest()
    object UserNotSaved : RegistryRequest()
    object Pending : RegistryRequest()
}