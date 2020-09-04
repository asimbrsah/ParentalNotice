package com.example.parentalnotice.di

import android.content.Context
import com.example.parentalnotice.data.source.local.SharedPreferenceImpl
import com.example.parentalnotice.data.source.local.SharedPreferenceStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CacheDataProviderModule {

    @Singleton
    @Provides
    fun provideSharedPreferenceStorage(context: Context): SharedPreferenceImpl {
        return SharedPreferenceStorage(context)
    }
}