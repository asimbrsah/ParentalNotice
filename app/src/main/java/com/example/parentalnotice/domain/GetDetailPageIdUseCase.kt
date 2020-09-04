package com.example.parentalnotice.domain

import com.example.parentalnotice.data.source.local.SharedPreferenceImpl
import com.example.parentalnotice.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetDetailPageIdUseCase @Inject constructor(
    private val sharedPreferenceImpl: SharedPreferenceImpl,
    @IODispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, Int>(dispatcher) {

    override suspend fun execute(parameters: Unit): Int {
        return sharedPreferenceImpl.detailPageId
    }
}