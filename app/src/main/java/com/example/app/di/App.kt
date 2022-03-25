package com.example.app.di

import android.app.Application
import com.example.app.di.components.AppComponent
import com.example.app.di.components.DaggerAppComponent

open class App : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}