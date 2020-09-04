package com.example.parentalnotice.di

import com.example.parentalnotice.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationProviderModule::class,
        ActivityBindingModule::class,
        ViewModelProviderModule::class,
        NetworkDataProviderModule::class,
        CacheDataProviderModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<BaseApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: BaseApplication): ApplicationComponent
    }
}
