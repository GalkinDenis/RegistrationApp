package com.example.app.data.datasource.localdatasource

import com.example.app.data.datasource.database.UsersDao
import com.example.app.data.datasource.database.UsersEntity
import com.example.app.domain.LogInRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private var dao: UsersDao,
) : LocalDataSource {

    override suspend fun saveUsers(users: String, password: String) {
        withContext(Dispatchers.IO) {
            dao.insert(
                UsersEntity(
                    email = users.email,
                    password = users.password,
                )
            )
        }
    }

    override suspend fun getUser(userEmail: String, password: String) =
        withContext(Dispatchers.IO) {
            val user = dao.getUser(userEmail)
            when {
                (userEmail == user.email && password == user.password) -> LogInRequest.UserFound(user.email, user.password)
                (userEmail == user.email && password != user.password) -> LogInRequest.IncorrectPassword
                else -> LogInRequest.UserNotFound
            }

        }

}

