package com.example.app.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UsersEntity::class], version = 1, exportSchema = false)
abstract class UsersRoomDatabase : RoomDatabase() {

    abstract fun itemDao(): UsersDao

}