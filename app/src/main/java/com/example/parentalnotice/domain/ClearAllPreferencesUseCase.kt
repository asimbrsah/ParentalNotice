package com.example.parentalnotice.domain

import com.example.parentalnotice.data.source.local.SharedPreferenceImpl
import com.example.parentalnotice.di.IODispatcher
import com.example.parentalnotice.domain.wrapper.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ClearAllPreferencesUseCase @Inject constructor(
    private val sharedPreferenceImpl: SharedPreferenceImpl,
    @IODispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, Unit>(dispatcher) {

    override suspend fun execute(parameters: Unit) {
        sharedPreferenceImpl.clearAllPreference()
    }
}