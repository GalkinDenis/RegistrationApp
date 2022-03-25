package com.example.app.di.modules

import com.example.app.data.authorizationrepositoryimpl.AuthorizationRepositoryImpl
import com.example.app.domain.repository.AuthorizationRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {

    @Singleton
    @Binds
    fun provideUsersRepository(repository: AuthorizationRepositoryImpl): AuthorizationRepository

}