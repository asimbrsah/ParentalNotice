package com.example.parentalnotice.di

import com.example.parentalnotice.presentation.launcher.LauncherActivity
import com.example.parentalnotice.presentation.launcher.LauncherModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            LauncherModule::class
        ]
    )
    abstract fun contributeLauncherActivity(): LauncherActivity
}