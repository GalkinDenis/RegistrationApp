package com.example.app.data.datasource

import com.example.app.data.datasource.localdatasource.LocalDataSource
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@BoundTo(UsersDataSource::class, SingletonComponent::class)
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