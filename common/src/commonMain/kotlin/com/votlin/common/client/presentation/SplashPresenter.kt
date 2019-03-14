package com.votlin.common.client.presentation

import com.votlin.common.client.domain.error.ErrorHandler
import com.votlin.common.client.domain.executor.Executor

class SplashPresenter(view: SplashView,
                      errorHandler: ErrorHandler,
                      executor: Executor
) :
        Presenter<SplashView>(view = view, errorHandler = errorHandler, executor = executor) {

    companion object {
        const val SPLASH_MILLIS: Long = 1000
    }

    override fun initialize() {
        view.showLoadingProgress(SPLASH_MILLIS)

    }

    fun onRunnableCallback() {
        view.goToTalksScreen()
    }
}

interface SplashView : Presenter.View {
    fun showLoadingProgress(delayMillis: Long)
    fun goToTalksScreen()
}