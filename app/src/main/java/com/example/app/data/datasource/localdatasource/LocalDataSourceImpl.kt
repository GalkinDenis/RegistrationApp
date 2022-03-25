package com.example.app.data.datasource.localdatasource

import android.util.Log
import com.example.app.data.datasource.database.UsersDao
import com.example.app.data.datasource.database.UsersEntity
import com.example.app.domain.LogInRequest
import com.example.app.domain.RegistryRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private var dao: UsersDao,
) : LocalDataSource {

    override suspend fun saveUsers(userEmail: String, password: String) =
        withContext(Dispatchers.IO) {
            val user = dao.getUser(userEmail)
            Log.d("TAGG", "${user?.email}  ${user?.password}")
            when (userEmail) {
                user?.email -> RegistryRequest.AlreadyExist
                else -> {
                    dao.insert(
                        UsersEntity(
                            email = userEmail,
                            password = password,
                        )
                    )
                    RegistryRequest.UserSaved
                }
            }
        }

    override suspend fun getUser(userEmail: String, password: String) =
        withContext(Dispatchers.IO) {
            val user = dao.getUser(userEmail)
            Log.d("TAGG", "userEmail = ${userEmail}  password = ${user?.password}")
            Log.d("TAGG", "dao = ${user?.email}  dao = ${user?.password}")
            when {
                (userEmail == user?.email && password == user.password) -> LogInRequest.UserFound(user.email, user.password)
                userEmail == user?.email -> LogInRequest.IncorrectPassword
                else -> LogInRequest.UserNotFound
            }

        }

}

