package com.example.app.domain.usecases

import com.example.app.domain.repository.AuthorizationRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val authorizationRepository: AuthorizationRepository
) {
    suspend operator fun invoke(emailUser: String, oldPassword: String, newPassword: String) =
        authorizationRepository.changePassword(emailUser, oldPassword, newPassword)
}