package com.example.app.data.datasource

import com.example.app.data.datasource.localdatasource.LocalDataSource
import javax.inject.Inject

class UsersDataSourceImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : UsersDataSource {

    override suspend fun registrationUser(userEmail: String, password: String) =
        localDataSource.registrationUser(userEmail, password)

    override suspend fun getUser(userEmail: String, password: String) =
        localDataSource.getUser(userEmail, password)

    override suspend fun changePassword(userEmail: String, oldPassword: String, newPassword: String) =
        localDataSource.changePassword(userEmail, oldPassword, newPassword)

}