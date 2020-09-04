package com.example.parentalnotice


object Constants {
    private const val APP_ID: String = BuildConfig.APPLICATION_ID
    val DEBUG: Boolean = BuildConfig.DEBUG

    // Preference related
    const val SHARED_PREF_NAME = APP_ID + BuildConfig.SHARED_PREF_NAME
    const val SHARED_PREF_PARAM_PAGE_ID = APP_ID + BuildConfig.SHARED_PREF_NAME + ".page_id"

    // Api related
    const val BASE_URL: String = BuildConfig.BASE_URL
}