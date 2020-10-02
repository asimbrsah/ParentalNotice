package com.example.parentalnotice.presentation.notice

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.parentalnotice.R
import com.example.parentalnotice.databinding.ActivityNoticeBinding
import com.example.parentalnotice.presentation.notice.NoticeFragmentDirections.toNoticeDetail
import com.example.parentalnotice.presentation.wrapper.provideActivityViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NoticeActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    lateinit var activityNoticeBinding: ActivityNoticeBinding
    private lateinit var navController: NavController

    lateinit var noticeViewModel: NoticeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNoticeBinding = DataBindingUtil.setContentView(this, R.layout.activity_notice)
        noticeViewModel = provideActivityViewModelProvider(viewModelProvider)

        navController = findNavController(R.id.fragment_nav_host)

        observeOnDetailPageViewed()
    }

    private fun observeOnDetailPageViewed() {
        noticeViewModel.detailPageId.observe(this, Observer { detailPageId ->
            if (detailPageId != null && detailPageId != 0) {
                navController.navigate(toNoticeDetail(detailPageId))
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}