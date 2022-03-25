package com.example.app.data.authorizationrepositoryimpl

import com.example.app.data.datasource.UsersDataSource
import com.example.app.domain.repository.AuthorizationRepository
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val usersDataSource: UsersDataSource,
) : AuthorizationRepository {

    override suspend fun saveUser(userEmail: String, password: String) =
        usersDataSource.saveUser(userEmail, password)


    override suspend fun getUser(userEmail: String, password: String) =
        usersDataSource.getUser(userEmail, password)

}