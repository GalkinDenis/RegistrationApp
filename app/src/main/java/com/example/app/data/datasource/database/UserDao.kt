package com.example.app.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: UsersEntity)

    @Query("SELECT email, password FROM users WHERE email = :userEmail")
    fun getUser(userEmail: String): UsersEntity

}