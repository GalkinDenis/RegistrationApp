package com.example.app.domain.entities

sealed class UserRegistrationRequest {
    object UserSaved : UserRegistrationRequest()
    object AlreadyExist : UserRegistrationRequest()
    object Pending : UserRegistrationRequest()
}