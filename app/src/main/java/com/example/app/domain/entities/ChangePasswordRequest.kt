package com.example.app.domain.entities

sealed class ChangePasswordRequest {
    object UserNotFound: ChangePasswordRequest()
    object PasswordChanged : ChangePasswordRequest()
    object OldPasswordIsNotCorrect : ChangePasswordRequest()
    object Pending : ChangePasswordRequest()
}