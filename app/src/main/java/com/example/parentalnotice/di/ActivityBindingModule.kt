package com.example.parentalnotice.di

import com.example.parentalnotice.presentation.notice.NoticeActivity
import com.example.parentalnotice.presentation.notice.NoticeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            NoticeModule::class
        ]
    )
    abstract fun contributeNoticeActivity(): NoticeActivity
}