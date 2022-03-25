package com.example.app.di.modules

import android.content.Context
import androidx.room.Room
import com.example.app.data.datasource.database.UsersDao
import com.example.app.data.datasource.database.UsersRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {

    @Singleton
    @Provides
    fun provideDao(dataBase: UsersRoomDatabase): UsersDao = dataBase.itemDao()

    @Singleton
    @Provides
    fun provideDataBase(context: Context): UsersRoomDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            UsersRoomDatabase::class.java,
            "users.db"
        ).build()

}