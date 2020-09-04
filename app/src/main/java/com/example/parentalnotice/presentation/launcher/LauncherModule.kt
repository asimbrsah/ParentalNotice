package com.example.parentalnotice.presentation.launcher

import androidx.lifecycle.ViewModel
import com.example.parentalnotice.di.ViewModelKey
import com.example.parentalnotice.presentation.launcher.notice.NoticeDetailFragment
import com.example.parentalnotice.presentation.launcher.notice.NoticeFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class LauncherModule {

    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel::class)
    internal abstract fun bindLauncherViewModel(launcherViewModel: LauncherViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeNoticeFragment(): NoticeFragment

    @ContributesAndroidInjector
    abstract fun contributeNoticeDetailFragment(): NoticeDetailFragment
}