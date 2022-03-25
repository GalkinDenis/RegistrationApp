package com.example.app.domain.usecases

import com.example.app.domain.LogInRequest
import com.example.app.domain.repository.AuthorizationRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val authorizationRepository: AuthorizationRepository
) {
    suspend operator fun invoke(emailUser: String, password: String): LogInRequest {
        return authorizationRepository.getUser(emailUser, password)
    }
}