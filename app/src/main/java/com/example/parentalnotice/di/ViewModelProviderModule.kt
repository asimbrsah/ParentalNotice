package com.example.parentalnotice.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelProviderModule {

    @Binds
    internal abstract fun bindViewModelProviderFactory(viewModelProviderFactory: ViewModelProviderFactory):ViewModelProvider.Factory
}