package com.votlin.client.presentation

import com.votlin.client.domain.error.ErrorHandler
import com.votlin.client.domain.getTalksUseCase
import com.votlin.client.domain.repository.Repository
import com.votlin.model.Talk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashPresenter(private val newThread: CoroutineDispatcher,
                      private val mainThread: CoroutineDispatcher,
                      private val repository: Repository,
                      view: SplashView,
                      errorHandler: ErrorHandler) :
        Presenter<SplashView>(view = view, errorHandler = errorHandler) {

    companion object {
        const val SPLASH_MILISECONDS: Long = 1000
    }

    override fun initialize() {
        view.showLoadingProgress(SPLASH_MILISECONDS)

        GlobalScope.launch(newThread) {
            val talks = getTalksUseCase(repository)
            withContext(mainThread) {
                view.showTalks(talks)
            }
        }
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
    fun showTalks(talks: List<Talk>)
}