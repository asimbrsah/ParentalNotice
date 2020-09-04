package com.example.parentalnotice.log

import timber.log.Timber
import timber.log.Timber.DebugTree

object TimberLogImplementation {
    fun init() {
        Timber.plant(object : DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
                return String.format(
                    "C: %s: %s %s",
                    element.lineNumber,
                    element.methodName,
                    super.createStackElementTag(element)
                )
            }
        })
    }
}