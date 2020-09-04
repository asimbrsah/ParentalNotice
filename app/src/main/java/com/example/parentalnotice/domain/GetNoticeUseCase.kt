package com.example.parentalnotice.domain

import com.example.parentalnotice.data.model.response.NoticeResponseModel
import com.example.parentalnotice.data.repository.NoticeRepositoryImpl
import com.example.parentalnotice.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetNoticeUseCase @Inject constructor(
    private val noticeRepositoryImpl: NoticeRepositoryImpl,
    @IODispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, List<NoticeResponseModel>>(dispatcher) {

    override suspend fun execute(parameters: Unit): List<NoticeResponseModel> {
        return noticeRepositoryImpl.getNotice()
    }
}