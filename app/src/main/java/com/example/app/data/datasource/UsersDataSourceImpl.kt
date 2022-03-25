package com.example.app.data.datasource

import com.example.app.data.datasource.localdatasource.LocalDataSource
import javax.inject.Inject

class UsersDataSourceImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : UsersDataSource {

    override suspend fun saveUser(userEmail: String, password: String) =
        localDataSource.saveUsers(userEmail, password)


    override suspend fun getUser(userEmail: String, password: String) =
        localDataSource.getUser(userEmail, password)

}