package com.example.app.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app.di.keys.ViewModelKey
import com.example.app.presentation.ViewModelFactory
import com.example.app.presentation.viewmodels.ChangePasswordViewModel
import com.example.app.presentation.viewmodels.LogInViewModel
import com.example.app.presentation.viewmodels.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(LogInViewModel::class)
    fun provideLogInViewModel(viewModel: LogInViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel::class)
    fun provideChangePasswordViewModel(viewModel: ChangePasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun provideRegistrationViewModel(viewModel: RegistrationViewModel): ViewModel


    @Binds
    @Singleton
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}