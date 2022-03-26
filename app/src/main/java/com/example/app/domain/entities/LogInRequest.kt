package com.example.app.domain.entities

sealed class LogInRequest {
    data class UserFound(val email: String, val password: String) : LogInRequest()
    object IncorrectPassword : LogInRequest()
    object UserNotFound : LogInRequest()
    object Pending : LogInRequest()
}