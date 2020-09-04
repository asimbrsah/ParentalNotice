package com.example.parentalnotice

import com.example.parentalnotice.di.DaggerApplicationComponent
import com.example.parentalnotice.log.TimberLogImplementation
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        // Initializing the timber log based on build type
        TimberLogImplementation.init()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(this)
    }
}