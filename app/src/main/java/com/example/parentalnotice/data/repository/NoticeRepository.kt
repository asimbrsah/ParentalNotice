package com.example.parentalnotice.data.repository

import com.example.parentalnotice.data.model.response.NoticeResponse
import com.example.parentalnotice.data.model.response.NoticeResponseModel
import com.example.parentalnotice.data.source.remote.RestApiEndPoint
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoticeRepository @Inject constructor(
    private val noticeDataSourceImpl: NoticeDataSourceImpl
) : NoticeRepositoryImpl {

    override suspend fun getNotice(): List<NoticeResponseModel> {
        return parseNoticeResponse(noticeDataSourceImpl.getNotice())
    }

    private fun parseNoticeResponse(notice: List<NoticeResponse?>?): List<NoticeResponseModel> {
        val noticeResponseList = mutableListOf<NoticeResponseModel>()
        if (notice != null && notice.isNotEmpty()) {
            notice.forEach { noticeResponse ->
                val parsedNotice = NoticeResponseModel(
                    userId = noticeResponse?.userId ?: -1,
                    id = noticeResponse?.id ?: -1,
                    title = noticeResponse?.title ?: "",
                    body = noticeResponse?.body ?: ""
                )
                noticeResponseList.add(parsedNotice)
            }
        }
        return noticeResponseList
    }
}

interface NoticeRepositoryImpl {
    suspend fun getNotice(): List<NoticeResponseModel>
}

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