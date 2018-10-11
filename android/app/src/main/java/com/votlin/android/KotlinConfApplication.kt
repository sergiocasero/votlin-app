package com.votlin.android

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import org.jetbrains.anko.AnkoLogger
import java.util.*

class KotlinConfApplication : Application(), AnkoLogger {
    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            println(throwable)
            throwable.printStackTrace()
            throwable?.cause?.printStackTrace()
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun getUserId(): String = "android-" + UUID.randomUUID().toString()
}