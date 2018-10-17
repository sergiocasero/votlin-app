package com.votlin.android.ui.activity

import android.os.Handler
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.votlin.android.R
import com.votlin.android.error.AndroidErrorHandler
import com.votlin.android.navigator.navigateToTalksActivity
import com.votlin.client.presentation.SplashPresenter
import com.votlin.client.presentation.SplashView

class SplashActivity : RootActivity<SplashView>(), SplashView {

    override val presenter: SplashPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_splash

    override val activityModule: Kodein.Module = Kodein.Module {
        bind<SplashPresenter>() with provider {
            SplashPresenter(view = this@SplashActivity,
                    errorHandler = AndroidErrorHandler())
        }
    }

    private var mDelayHandler: Handler? = null

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            presenter.onRunnableCallback()
        }
    }

    override fun initializeUI() {
        mDelayHandler = Handler()

        //val local = object : LocalDataSource {
        //    override fun getFavoriteTalks(): List<Talk> {
        //        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //    }
//
        //    override fun saveRate(rate: Rate) {
        //        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //    }
//
        //    override fun saveTalk(talk: Talk) {
        //        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //    }
//
        //}
//
        //val remote: RemoteDataSource = CommonRemoteDataSource()
//
        //val repository: Repository = VotlinRepository(remote = remote, local = local)
    }

    override fun registerListeners() {
        // Nothing to do yet
    }

    override fun showProgress() {
        // Nothing to do yet
    }

    override fun hideProgress() {
        // Nothing to do yet
    }

    override fun goToTalksScreen() {
        navigateToTalksActivity(context = this)
        finish()
    }

    override fun showLoadingProgress(delayMillis: Long) {
        mDelayHandler!!.postDelayed(mRunnable, delayMillis)
    }
}