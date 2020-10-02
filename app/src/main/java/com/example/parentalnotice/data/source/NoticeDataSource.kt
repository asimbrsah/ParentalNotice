package com.example.parentalnotice.data.source

import com.example.parentalnotice.data.model.response.NoticeResponse
import com.example.parentalnotice.data.source.remote.RestApiEndPoint
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoticeDataSource @Inject constructor(
    private val restApiEndPoint: RestApiEndPoint
) : NoticeDataSourceImpl {

    override suspend fun getNotice(): List<NoticeResponse?>? {
        return restApiEndPoint.getNotice()
    }
}

interface NoticeDataSourceImpl {
    suspend fun getNotice(): List<NoticeResponse?>?
}