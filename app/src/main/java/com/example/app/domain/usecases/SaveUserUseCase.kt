package com.example.app.domain.usecases

import com.example.app.domain.repository.AuthorizationRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val authorizationRepository: AuthorizationRepository
) {
    suspend operator fun invoke(user: String, password: String) =
        authorizationRepository.saveUser(user, password)

}