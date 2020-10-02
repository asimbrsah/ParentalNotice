package com.example.parentalnotice.presentation.notice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parentalnotice.data.model.response.NoticeResponseModel
import com.example.parentalnotice.databinding.FragmentNoticeBinding
import com.example.parentalnotice.presentation.wrapper.provideActivityViewModelProviderIntoFragment
import com.example.parentalnotice.presentation.notice.NoticeFragmentDirections.toNoticeDetail
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class NoticeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    private lateinit var fragmentNoticeBinding: FragmentNoticeBinding

    private lateinit var noticeViewModel: NoticeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        noticeViewModel = provideActivityViewModelProviderIntoFragment(viewModelProvider)

        fragmentNoticeBinding = FragmentNoticeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return fragmentNoticeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLoading()
        observeRemoteError()
        observeNoticeResponse()
    }

    private fun observeLoading() {
        noticeViewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            if (loading != null && loading) {
                fragmentNoticeBinding.includeCircularProgress.circularProgressBar.visibility =
                    View.VISIBLE
            } else {
                fragmentNoticeBinding.includeCircularProgress.circularProgressBar.visibility =
                    View.GONE
            }
        })
    }

    private fun observeRemoteError() {
        noticeViewModel.remoteError.observe(viewLifecycleOwner, Observer { remoteError ->
            if (remoteError != null && remoteError.isNotEmpty()) {
                Toast.makeText(requireContext(), remoteError, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun observeNoticeResponse() {
        noticeViewModel.noticeResponse.observe(viewLifecycleOwner, Observer { noticeResponse ->
            if (noticeResponse != null && noticeResponse.isNotEmpty()) {
                val noticeAdapter =
                    NoticeAdapter(
                        object :
                            NoticeAdapter.NoticeAdapterCallback {
                            override fun onNoticeClicked(
                                position: Int,
                                noticeResponseModel: NoticeResponseModel
                            ) {
                                findNavController().navigate(toNoticeDetail(noticeResponseModel.id))
                            }
                        })
                noticeAdapter.submitList(noticeResponse)
                fragmentNoticeBinding.rvNotice.adapter = noticeAdapter
                fragmentNoticeBinding.rvNotice.visibility = View.VISIBLE
            } else {
                fragmentNoticeBinding.rvNotice.visibility = View.GONE
            }
        })
    }
}