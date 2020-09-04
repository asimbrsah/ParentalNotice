package com.example.parentalnotice.log

import android.util.Log
import timber.log.Timber

object TimberLogImplementation {
    fun init() {
        // A tree which logs important information for crash reporting.
        Timber.plant(object : Timber.Tree() {
            override fun isLoggable(tag: String?, priority: Int): Boolean {
                // Don't log VERBOSE, DEBUG and INFO
                // Log only ERROR, WARN and WTF
                return priority != Log.VERBOSE && priority != Log.DEBUG && priority != Log.INFO
            }

            override fun log(
                priority: Int,
                tag: String?,
                message: String,
                t: Throwable?
            ) {
                if (isLoggable(tag, priority)) {
                    // log your crash to your favourite
                    // Sending crash report to Firebase CrashAnalytics

                    // FirebaseCrash.report(message);
                    // FirebaseCrash.report(new Exception(message));
                }
            }
        })
    }
}