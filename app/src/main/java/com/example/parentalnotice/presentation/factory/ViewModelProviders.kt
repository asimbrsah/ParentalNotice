package com.example.parentalnotice.presentation.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * For Activity
 * */
inline fun <reified VM : ViewModel> FragmentActivity.provideActivityViewModelProvider(
    provider: ViewModelProvider.Factory
) = ViewModelProvider(this, provider).get(VM::class.java)

/**
 * For Fragment
 * */
inline fun <reified VM : ViewModel> Fragment.provideFragmentViewModelProvider(
    provider: ViewModelProvider.Factory
) = ViewModelProvider(this, provider).get(VM::class.java)

/**
 * Activity ViewModel on fragment
 * */
inline fun <reified VM : ViewModel> Fragment.provideActivityViewModelProviderIntoFragment(
    provider: ViewModelProvider.Factory
) = ViewModelProvider(requireActivity(), provider).get(VM::class.java)