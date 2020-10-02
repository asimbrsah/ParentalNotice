package com.example.parentalnotice.domain

import com.example.parentalnotice.data.source.local.SharedPreferenceImpl
import com.example.parentalnotice.di.IODispatcher
import com.example.parentalnotice.domain.wrapper.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveDetailPageIdUseCase @Inject constructor(
    private val sharedPreferenceImpl: SharedPreferenceImpl,
    @IODispatcher dispatcher: CoroutineDispatcher
) : UseCase<Int, Unit>(dispatcher) {

    override suspend fun execute(parameters: Int) {
        sharedPreferenceImpl.detailPageId = parameters
    }
}