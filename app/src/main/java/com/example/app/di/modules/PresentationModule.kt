package com.example.app.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app.di.keys.ViewModelKey
import com.example.app.presentation.ViewModelFactory
import com.example.app.presentation.viewmodels.ChangePasswordViewModel
import com.example.app.presentation.viewmodels.LogInViewModel
import com.example.app.presentation.viewmodels.RegistryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface PresentationModule {

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(LogInViewModel::class)
    fun provideUserViewModel(viewModel: LogInViewModel): ViewModel


    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(ChangePasswordViewModel::class)
    fun provideUserCardViewModel(viewModel: ChangePasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(RegistryViewModel::class)
    fun provideRegistryViewModel(viewModel: RegistryViewModel): ViewModel


    @Binds
    @Singleton
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}