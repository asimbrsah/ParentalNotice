package com.example.parentalnotice.presentation.notice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parentalnotice.data.model.response.NoticeResponseModel
import com.example.parentalnotice.domain.GetDetailPageIdUseCase
import com.example.parentalnotice.domain.GetNoticeUseCase
import com.example.parentalnotice.domain.wrapper.Result
import com.example.parentalnotice.domain.SaveDetailPageIdUseCase
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class NoticeViewModel @Inject constructor(
    private val getDetailPageIdUseCase: GetDetailPageIdUseCase,
    private val saveDetailPageIdUseCase: SaveDetailPageIdUseCase,
    private val getNoticeUseCase: GetNoticeUseCase
) : ViewModel() {

    private val _detailPageId = MutableLiveData<Int>()
    val detailPageId: LiveData<Int>
        get() = _detailPageId

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _remoteError = MutableLiveData<String>()
    val remoteError: LiveData<String>
        get() = _remoteError

    private val _noticeResponse = MutableLiveData<List<NoticeResponseModel>>()
    val noticeResponse: LiveData<List<NoticeResponseModel>>
        get() = _noticeResponse

    init {
        viewModelScope.launch {
            _loading.value = true
            when (val detailPageIdUseCase = getDetailPageIdUseCase(Unit)) {
                is Result.Success -> {
                    _detailPageId.value = detailPageIdUseCase.data
                }
                is Result.Error -> {
                    Timber.e(detailPageIdUseCase.exception.localizedMessage)
                }
            }
            when (val noticeResponse = getNoticeUseCase(Unit)) {
                is Result.Success -> {
                    _noticeResponse.value = noticeResponse.data
                    _loading.value = false
                }
                is Result.Error -> {
                    Timber.e(noticeResponse.exception.localizedMessage)
                    _remoteError.value = "Something went wrong..."
                    _loading.value = false
                }
            }
        }
    }

    fun saveWhenDetailPageViewedAndQuitTheApp(value: Int) {
        viewModelScope.launch {
            when (val isDetailPageViewedUseCase = saveDetailPageIdUseCase(value)) {
                is Result.Success -> {
                    Timber.d("Page navigation")
                }
                is Result.Error -> {
                    Timber.e(isDetailPageViewedUseCase.exception.localizedMessage)
                }
            }
        }
    }
}