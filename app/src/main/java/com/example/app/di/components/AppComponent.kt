package com.example.app.di.components

import android.content.Context
import com.example.app.di.modules.*
import com.example.app.ui.AboutFragment
import com.example.app.ui.ChangePasswordFragment
import com.example.app.ui.LogInFragment
import com.example.app.ui.RegistryFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        DataRepositoryModule::class,
        DomainModule::class,
        PresentationModule::class,
    ]
)
interface AppComponent {

    @Singleton
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun injectLogInFragment(fragment: LogInFragment)
    fun injectChangePasswordFragment(fragment: ChangePasswordFragment)
    fun injectAboutFragment(fragment: AboutFragment)
    fun injectRegistryFragment(fragment: RegistryFragment)
}