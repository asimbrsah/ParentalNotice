package com.example.parentalnotice.presentation.launcher

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.parentalnotice.R
import com.example.parentalnotice.databinding.ActivityLauncherBinding
import com.example.parentalnotice.presentation.factory.provideActivityViewModelProvider
import com.example.parentalnotice.presentation.launcher.notice.NoticeFragmentDirections.toNoticeDetail
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LauncherActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    lateinit var activityLauncherBinding: ActivityLauncherBinding
    private lateinit var navController: NavController

    lateinit var launcherViewModel: LauncherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLauncherBinding = DataBindingUtil.setContentView(this, R.layout.activity_launcher)
        launcherViewModel = provideActivityViewModelProvider(viewModelProvider)

        navController = findNavController(R.id.fragment_nav_host)

        observeOnDetailPageViewed()
    }

    private fun observeOnDetailPageViewed() {
        launcherViewModel.detailPageId.observe(this, Observer { detailPageId ->
            if (detailPageId != null && detailPageId != 0) {
                navController.navigate(toNoticeDetail(detailPageId))
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}