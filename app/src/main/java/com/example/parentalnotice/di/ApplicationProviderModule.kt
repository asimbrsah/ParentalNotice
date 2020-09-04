package com.example.parentalnotice.di

import android.app.Application
import android.content.Context
import com.example.parentalnotice.BaseApplication
import com.example.parentalnotice.Constants
import com.example.parentalnotice.data.source.remote.RestApiEndPoint
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApplicationProviderModule {

    /**
     * Application context
     * */
    @Provides
    fun provideApplicationContext(application: BaseApplication): Context {
        return application.applicationContext
    }

    /**
     * Singleton Cache instance on network layer to provide cache size
     * */
    @Singleton
    @Provides
    fun provideCache(context: Context): Cache {
        val cacheSize = 2 * 1024 * 1024.toLong() // 2 MB
        return Cache(context.cacheDir, cacheSize)
    }

    /**
     * Singleton okHttpClient instance to apply on the network layer
     * Interceptor logic can be applied here for token process, sessions, logging etc...
     * */
    @Singleton
    @Provides
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        if (Constants.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(logging)
        }
        okHttpClientBuilder.cache(cache)
        return okHttpClientBuilder.build()
    }

    /**
     * Singleton GSon instance to apply on the network layer for serialization process
     * Naming policy can be applied from here to follow the similar pattern from payload key
     * */
    @Singleton
    @Provides
    fun provideGSon(): Gson {
        val gSonBuilder = GsonBuilder()
        gSonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        return gSonBuilder.create()
    }

    /**
     * Singleton Retrofit instance to apply for consuming http payload
     * */
    @Singleton
    @Provides
    fun provideRetrofitInstance(gSon: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gSon))
            .client(okHttpClient)
            .build()
    }

    /**
     * Singleton retrofit instance to be provided
     * */
    @Singleton
    @Provides
    fun provideRestApiEndPoint(retrofit: Retrofit): RestApiEndPoint {
        return retrofit.create(RestApiEndPoint::class.java)
    }

    /**
     * Co-routine dispatcher
     * */
    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IODispatcher
    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @MainImmediateDispatcher
    @Provides
    fun providesMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate
}