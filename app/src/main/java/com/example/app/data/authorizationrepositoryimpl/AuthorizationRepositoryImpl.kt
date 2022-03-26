package com.example.app.data.authorizationrepositoryimpl

import com.example.app.data.datasource.UsersDataSource
import com.example.app.domain.repository.AuthorizationRepository
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@BoundTo(AuthorizationRepository::class, SingletonComponent::class)
class AuthorizationRepositoryImpl @Inject constructor(
    private val usersDataSource: UsersDataSource,
) : AuthorizationRepository {

    override suspend fun registrationUser(userEmail: String, password: String) =
        usersDataSource.registrationUser(userEmail, password)

    override suspend fun getUser(userEmail: String, password: String) =
        usersDataSource.getUser(userEmail, password)

    override suspend fun changePassword(userEmail: String, password: String, newPassword: String) =
        usersDataSource.changePassword(userEmail, password, newPassword)

}