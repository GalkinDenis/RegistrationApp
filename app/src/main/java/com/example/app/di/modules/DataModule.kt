package com.example.app.di.modules

import android.content.Context
import androidx.room.Room
import com.example.app.data.datasource.localdatasource.database.UsersDao
import com.example.app.data.datasource.localdatasource.database.UsersRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideDao(dataBase: UsersRoomDatabase): UsersDao = dataBase.itemDao()

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): UsersRoomDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            UsersRoomDatabase::class.java,
            "users.db"
        ).build()

}