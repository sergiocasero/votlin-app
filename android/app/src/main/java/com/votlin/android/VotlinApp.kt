package com.votlin.android

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.lazy

class VotlinApp : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(app(this@VotlinApp))
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}