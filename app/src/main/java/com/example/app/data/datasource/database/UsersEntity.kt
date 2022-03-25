package com.example.app.data.datasource.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app.domain.entities.User

@Entity(tableName = "users")
data class UsersEntity(

    @PrimaryKey
    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "password")
    val password: String,

)

fun UsersEntity.toUser(): User {
    return User(
        email = this.email,
        password = this.password,
    )
}