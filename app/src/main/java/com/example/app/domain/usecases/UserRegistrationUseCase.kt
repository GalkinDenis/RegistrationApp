package com.example.app.domain.usecases

import com.example.app.domain.repository.AuthorizationRepository
import javax.inject.Inject

class UserRegistrationUseCase @Inject constructor(
    private val authorizationRepository: AuthorizationRepository
) {
    suspend operator fun invoke(user: String, password: String) =
        authorizationRepository.registrationUser(user, password)

}