package com.example.parentalnotice.presentation.notice

import androidx.lifecycle.ViewModel
import com.example.parentalnotice.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class NoticeModule {

    @Binds
    @IntoMap
    @ViewModelKey(NoticeViewModel::class)
    internal abstract fun bindNoticeViewModel(noticeViewModel: NoticeViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeNoticeFragment(): NoticeFragment

    @ContributesAndroidInjector
    abstract fun contributeNoticeDetailFragment(): NoticeDetailFragment
}