package com.example.parentalnotice.presentation.launcher.notice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.parentalnotice.databinding.FragmentNoticeDetailBinding
import com.example.parentalnotice.presentation.factory.provideActivityViewModelProviderIntoFragment
import com.example.parentalnotice.presentation.launcher.LauncherViewModel
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject


class NoticeDetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    private lateinit var launcherViewModel: LauncherViewModel

    private lateinit var fragmentNoticeDetailBinding: FragmentNoticeDetailBinding

    private var noticeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launcherViewModel = provideActivityViewModelProviderIntoFragment(viewModelProvider)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    launcherViewModel.saveWhenDetailPageViewedAndQuitTheApp(0)
                    findNavController().navigateUp()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentNoticeDetailBinding =
            FragmentNoticeDetailBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
            }

        return fragmentNoticeDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireNotNull(arguments).apply {
            noticeId = NoticeDetailFragmentArgs.fromBundle(this).id
        }

        launcherViewModel.saveWhenDetailPageViewedAndQuitTheApp(noticeId)

        observeLoading()
        observeRemoteError()
        observeNoticeResponse()
    }

    private fun observeLoading() {
        launcherViewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            if (loading != null && loading) {
                fragmentNoticeDetailBinding.includeCircularProgress.circularProgressBar.visibility =
                    View.VISIBLE
            } else {
                fragmentNoticeDetailBinding.includeCircularProgress.circularProgressBar.visibility =
                    View.GONE
            }
        })
    }

    private fun observeRemoteError() {
        launcherViewModel.remoteError.observe(viewLifecycleOwner, Observer { remoteError ->
            if (remoteError != null && remoteError.isNotEmpty()) {
                Toast.makeText(requireContext(), remoteError, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun observeNoticeResponse() {
        launcherViewModel.noticeResponse.observe(viewLifecycleOwner, Observer { noticeResponse ->
            if (noticeResponse != null && noticeResponse.isNotEmpty()) {
                if (noticeId != 0) {
                    Timber.d("Passed notice id  :-  %s", noticeId)
                    noticeResponse.forEach {
                        if (it.id == noticeId) {
                            fragmentNoticeDetailBinding.apply {
                                tvNumber.text = "Notice number ${it.id}"
                                tvTitle.text = it.title
                                tvBody.text = it.body
                                fragmentNoticeDetailBinding.lytCard.visibility = View.VISIBLE
                                return@forEach
                            }
                        }
                    }
                }
            } else {
                findNavController().navigateUp()
            }
        })
    }
}