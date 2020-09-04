package com.example.parentalnotice.data.source.remote

import com.example.parentalnotice.data.model.response.NoticeResponse
import retrofit2.http.GET

interface RestApiEndPoint {

    @GET("/posts")
    suspend fun getNotice(): List<NoticeResponse?>?
}