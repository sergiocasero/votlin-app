package com.votlin.client.presentation

import com.votlin.client.domain.error.ErrorHandler

class SplashPresenter(view: SplashView, errorHandler: ErrorHandler) :
        Presenter<SplashView>(view = view, errorHandler = errorHandler) {

    companion object {
        const val SPLASH_MILISECONDS: Long = 1000
    }

    override fun initialize() {
        view.showLoadingProgress(SPLASH_MILISECONDS)
    }

    override fun destroy() {
        // Nothing to do yet
    }

    fun onRunnableCallback() {
        // view.goToTalksScreen()
    }
}

interface SplashView : Presenter.View {
    fun showLoadingProgress(delayMillis: Long)
    fun goToTalksScreen()
}