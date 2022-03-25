package com.example.app.di.modules

import com.example.app.data.datasource.UsersDataSource
import com.example.app.data.datasource.UsersDataSourceImpl
import com.example.app.data.datasource.localdatasource.LocalDataSource
import com.example.app.data.datasource.localdatasource.LocalDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataRepositoryModule {

    @Binds
    fun provideUsersDataSource(dataSource: UsersDataSourceImpl): UsersDataSource

    @Binds
    fun provideLocalDataSource(dataSource: LocalDataSourceImpl): LocalDataSource

}