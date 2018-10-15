package com.votlin.android.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.votlin.android.R
import com.votlin.android.error.AndroidErrorHandler
import com.votlin.client.presentation.TalksPresenter
import com.votlin.client.presentation.TalksView
import com.votlin.model.Talk

class TalksActivity : RootActivity<TalksView>(),TalksView {

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, TalksActivity::class.java)
    }

    override val presenter: TalksPresenter by instance()

   override val layoutResourceId: Int = R.layout.activity_talks
    override val activityModule: Kodein.Module = Kodein.Module{
        bind<TalksPresenter>() with provider {
            TalksPresenter(errorHandler = AndroidErrorHandler(),
                    view =this@TalksActivity)
        }
    }

    override fun initializeUI() {
        // Nothing to do yet
    }

    override fun registerListeners() {
        // Nothing to do yet.
    }

    override fun showProgress() {
        // Nothing to do yet
    }

    override fun hideProgress() {
        // Nothing to do yet
    }

    override fun showTalks(talks: List<Talk>) {
        // Nothing to do yet
        Log.i("Main activity", talks.toString())
    }

}
