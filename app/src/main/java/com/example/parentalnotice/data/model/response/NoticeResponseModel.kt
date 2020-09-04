package com.example.parentalnotice.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class NoticeResponse(
    @SerializedName("userId")
    val userId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("body")
    val body: String?
)

/**
 * Model to use in presentation layer
 * */
@Parcelize
data class NoticeResponseModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) : Parcelable