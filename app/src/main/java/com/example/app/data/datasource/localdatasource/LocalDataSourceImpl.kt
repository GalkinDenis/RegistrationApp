package com.example.app.data.datasource.localdatasource

import com.example.app.data.datasource.localdatasource.database.UsersDao
import com.example.app.data.datasource.localdatasource.database.UsersEntity
import com.example.app.domain.entities.ChangePasswordRequest
import com.example.app.domain.entities.LogInRequest
import com.example.app.domain.entities.UserRegistrationRequest
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@BoundTo(LocalDataSource::class, SingletonComponent::class)
class LocalDataSourceImpl @Inject constructor(
    private var dao: UsersDao,
) : LocalDataSource {

    override suspend fun registrationUser(userEmail: String, password: String) =
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            val user = dao.getUser(userEmail)
            when (userEmail) {
                user?.email -> UserRegistrationRequest.AlreadyExist
                else -> {
                    dao.insert(
                        UsersEntity(
                            email = userEmail,
                            password = password,
                        )
                    )
                    UserRegistrationRequest.UserSaved
                }
            }
        }

    override suspend fun getUser(userEmail: String, password: String) =
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            val user = dao.getUser(userEmail)
            when {
                (userEmail == user?.email && password == user.password) -> LogInRequest.UserFound(user.email, user.password)
                userEmail == user?.email -> LogInRequest.IncorrectPassword
                else -> LogInRequest.UserNotFound
            }

        }

    override suspend fun changePassword(userEmail: String, oldPassword: String, newPassword: String) =
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            val user = dao.getUser(userEmail)
            when {
                userEmail != user?.email -> ChangePasswordRequest.UserNotFound
                oldPassword != user.password -> ChangePasswordRequest.OldPasswordIsNotCorrect
                else -> {
                    dao.insert(
                        UsersEntity(
                            email = userEmail,
                            password = newPassword,
                        )
                    )
                    ChangePasswordRequest.PasswordChanged
                }
            }
        }
}

