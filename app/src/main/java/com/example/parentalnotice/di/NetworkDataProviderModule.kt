package com.example.parentalnotice.di

import com.example.parentalnotice.data.repository.NoticeRepository
import com.example.parentalnotice.data.repository.NoticeRepositoryImpl
import com.example.parentalnotice.data.source.NoticeDataSource
import com.example.parentalnotice.data.source.NoticeDataSourceImpl
import com.example.parentalnotice.data.source.remote.RestApiEndPoint
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NetworkDataProviderModule {

    @Singleton
    @Provides
    fun provideNoticeDataSource(restApiEndPoint: RestApiEndPoint): NoticeDataSourceImpl {
        return NoticeDataSource(restApiEndPoint)
    }

    @Singleton
    @Provides
    fun provideNoticeRepository(noticeDataSource: NoticeDataSource): NoticeRepositoryImpl {
        return NoticeRepository(noticeDataSource)
    }
}